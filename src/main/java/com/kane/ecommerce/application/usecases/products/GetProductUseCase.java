package com.kane.ecommerce.application.usecases.products;

import com.kane.ecommerce.adapters.exception.ResourceNotFoundException;
import com.kane.ecommerce.domain.models.Product;
import com.kane.ecommerce.domain.repositories.ProductRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class GetProductUseCase {
  private final ProductRepository productRepository;

  @Autowired
  public GetProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product run(@Min(value = 1L, message = "Invalid product ID.") long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
  }
}
