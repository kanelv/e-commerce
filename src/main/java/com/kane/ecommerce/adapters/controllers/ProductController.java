package com.kane.ecommerce.adapters.controllers;

import com.kane.ecommerce.application.usecases.products.GetAllProductUseCase;
import com.kane.ecommerce.domain.models.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  final private GetAllProductUseCase getAllProductUseCase;

  @Autowired
  public ProductController(GetAllProductUseCase getAllProductUseCase) {
    this.getAllProductUseCase = getAllProductUseCase;
  }

  @GetMapping(value = {"", "/"})
  public @NotNull Iterable<Product> getProducts() {
    return getAllProductUseCase.run();
  }
}
