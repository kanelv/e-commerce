package com.kane.ecommerce.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orderProducts")
public class OrderProduct {

  @EmbeddedId
  @JsonIgnore
  private OrderProductPK pk;

  @Column(nullable = false) private Integer quantity;

  public OrderProduct() {
    super();
  }

  public OrderProduct(Order order, Product product, Integer quantity) {
    pk = new OrderProductPK();
    pk.setOrder(order);
    pk.setProduct(product);
    this.quantity = quantity;
  }

  @Transient
  public Product getProduct() {
    return this.pk.getProduct();
  }

  @Transient
  public Double getTotalPrice() {
    return getProduct().getPrice() * getQuantity();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pk == null) ? 0 : pk.hashCode());

    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OrderProduct other = (OrderProduct) obj;
    if (pk == null) {
      if (other.pk != null) {
        return false;
      }
    } else if (!pk.equals(other.pk)) {
      return false;
    }

    return true;
  }
}
