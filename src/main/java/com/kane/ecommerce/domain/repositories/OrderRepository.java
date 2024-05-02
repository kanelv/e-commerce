package com.kane.ecommerce.domain.repositories;

import com.kane.ecommerce.domain.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
