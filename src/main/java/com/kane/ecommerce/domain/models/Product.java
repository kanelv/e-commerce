package com.kane.ecommerce.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "Product name is required.")
  @Basic(optional = false)
  private String name;

  private Double price;

  private String pictureUrl;

  public Product(Long id, @NotNull(message = "Product name is required.") String name, Double price, String pictureUrl) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.pictureUrl = pictureUrl;
  }

  public Product() {}
}
