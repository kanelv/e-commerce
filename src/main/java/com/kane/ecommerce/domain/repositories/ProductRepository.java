package com.kane.ecommerce.domain.repositories;

import com.kane.ecommerce.domain.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
