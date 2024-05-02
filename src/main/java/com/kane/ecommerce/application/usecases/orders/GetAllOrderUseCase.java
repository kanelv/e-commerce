package com.kane.ecommerce.application.usecases.orders;

import com.kane.ecommerce.domain.models.Order;
import com.kane.ecommerce.domain.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllOrderUseCase {
  final private OrderRepository orderRepository;

  @Autowired
  public GetAllOrderUseCase(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Iterable<Order> run() {
    return this.orderRepository.findAll();
  }
}
