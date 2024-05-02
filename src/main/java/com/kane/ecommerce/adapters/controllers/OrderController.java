package com.kane.ecommerce.adapters.controllers;

import com.kane.ecommerce.adapters.dtos.OrderProductDto;
import com.kane.ecommerce.adapters.exception.ResourceNotFoundException;
import com.kane.ecommerce.application.usecases.orderproducts.CreateOrderProductUseCase;
import com.kane.ecommerce.application.usecases.orders.CreateOrderUseCase;
import com.kane.ecommerce.application.usecases.orders.GetAllOrderUseCase;
import com.kane.ecommerce.application.usecases.orders.UpdateOrderUseCase;
import com.kane.ecommerce.application.usecases.products.GetProductUseCase;
import com.kane.ecommerce.domain.models.Order;
import com.kane.ecommerce.domain.models.OrderProduct;
import com.kane.ecommerce.domain.models.OrderStatus;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  GetAllOrderUseCase getAllOrderUseCase;
  CreateOrderUseCase createOrderUseCase;
  CreateOrderProductUseCase createOrderProductUseCase;
  GetProductUseCase getProductUseCase;
  UpdateOrderUseCase updateOrderUseCase;

  @Autowired
  public OrderController(
      GetAllOrderUseCase getAllOrderUseCase,
      CreateOrderUseCase createOrderUseCase,
      CreateOrderProductUseCase createOrderProductUseCase,
      GetProductUseCase getProductUseCase,
      UpdateOrderUseCase updateOrderUseCase) {
    this.getAllOrderUseCase = getAllOrderUseCase;
    this.createOrderUseCase = createOrderUseCase;
    this.createOrderProductUseCase = createOrderProductUseCase;
    this.getProductUseCase = getProductUseCase;
    this.updateOrderUseCase = updateOrderUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public @NotNull Iterable<Order> list() {
    return this.getAllOrderUseCase.run();
  }

  @PostMapping
  public ResponseEntity<Order> create(@RequestBody OrderForm form) {
    List<OrderProductDto> formDtos = form.getProductOrders();
    validateProductsExistence(formDtos);
    Order order = new Order();
    order.setStatus(OrderStatus.PAID.name());
    order = this.createOrderUseCase.run(order);

    List<OrderProduct> orderProducts = new ArrayList<>();
    for (OrderProductDto dto : formDtos) {
      orderProducts.add(
          createOrderProductUseCase.run(
              new OrderProduct(
                  order, getProductUseCase.run(dto.getProduct().getId()), dto.getQuantity())));
    }

    order.setOrderProducts(orderProducts);

    this.updateOrderUseCase.run(order);

    String uri =
        ServletUriComponentsBuilder.fromCurrentServletMapping()
            .path("/orders/{id}")
            .buildAndExpand(order.getId())
            .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);

    return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
  }

  private void validateProductsExistence(List<OrderProductDto> orderProducts) {
    List<OrderProductDto> list =
        orderProducts.stream()
            .filter(op -> Objects.isNull(this.getProductUseCase.run(op.getProduct().getId())))
            .toList();

    if (!CollectionUtils.isEmpty(list)) {
      throw new ResourceNotFoundException("Product not found");
    }
  }

  public static class OrderForm {

    private List<OrderProductDto> productOrders;

    public List<OrderProductDto> getProductOrders() {
      return productOrders;
    }

    public void setProductOrders(List<OrderProductDto> productOrders) {
      this.productOrders = productOrders;
    }
  }
}
