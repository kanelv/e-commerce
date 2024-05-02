package com.kane.ecommerce.application.usecases.products;

import com.kane.ecommerce.domain.models.Product;
import com.kane.ecommerce.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class GetAllProductUseCase {
  private final ProductRepository productRepository;

  @Autowired
  public GetAllProductUseCase(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Iterable<Product> run() {
    return productRepository.findAll();
  }
}
