package com.kane.ecommerce.domain.repositories;

import com.kane.ecommerce.domain.models.OrderProduct;
import com.kane.ecommerce.domain.models.OrderProductPK;
import org.springframework.data.repository.CrudRepository;
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
