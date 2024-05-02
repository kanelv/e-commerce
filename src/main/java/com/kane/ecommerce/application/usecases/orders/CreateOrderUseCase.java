package com.kane.ecommerce.application.usecases.orders;

import com.kane.ecommerce.domain.models.Order;
import com.kane.ecommerce.domain.repositories.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
@Transactional
public class CreateOrderUseCase {
  private final OrderRepository orderRepository;

  @Autowired
  public CreateOrderUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order run(@NotNull(message = "The order cannot be null.") @Valid Order order) {
    order.setDateCreated(LocalDate.now());

    return this.orderRepository.save(order);
  }
}
