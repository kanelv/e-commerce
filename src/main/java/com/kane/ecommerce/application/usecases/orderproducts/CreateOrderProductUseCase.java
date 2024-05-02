package com.kane.ecommerce.application.usecases.orderproducts;

import com.kane.ecommerce.domain.models.OrderProduct;
import com.kane.ecommerce.domain.repositories.OrderProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
public class CreateOrderProductUseCase {
  private final OrderProductRepository orderProductRepository;

  @Autowired
  public CreateOrderProductUseCase(OrderProductRepository orderProductRepository) {
    this.orderProductRepository = orderProductRepository;
  }

  public OrderProduct run(
      @NotNull(message = "The products for order cannot be null.") @Valid
          OrderProduct orderProduct) {
    return this.orderProductRepository.save(orderProduct);
  }
}
