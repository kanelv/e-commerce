package com.kane.ecommerce.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy") private LocalDate dateCreated;

  private String status;

  @OneToMany(mappedBy = "pk.order")
  @Valid
  private List<OrderProduct> orderProducts = new ArrayList<>();

  @Transient
  public Double getTotalOrderPrice() {
    double sum = 0D;
    List<OrderProduct> orderProducts = getOrderProducts();
    for (OrderProduct op : orderProducts) {
      sum += op.getTotalPrice();
    }

    return sum;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setOrderProducts(List<OrderProduct> orderProducts) {
    this.orderProducts = orderProducts;
  }

  @Transient
  public int getNumberOfProducts() {
    return this.orderProducts.size();
  }
}
