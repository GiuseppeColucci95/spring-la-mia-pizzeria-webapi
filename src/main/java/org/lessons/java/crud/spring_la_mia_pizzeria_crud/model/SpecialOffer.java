package org.lessons.java.crud.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "special_offers")
public class SpecialOffer {

  // ! VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @FutureOrPresent(message = "Start date cannot be in the past")
  @NotNull(message = "Start Date cannot be null")
  private LocalDate startDate;

  @FutureOrPresent(message = "End date cannot be in the past")
  private LocalDate endDate;

  @NotBlank(message = "Offer title cannot be null or blank")
  private String title;

  // ! MANY TO ONE RELATIONS
  @ManyToOne
  @JoinColumn(name = "pizza_id", nullable = false)
  @JsonBackReference
  private Pizza pizza;

  // ! GETTERS AND SETTERS
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Pizza getPizza() {
    return pizza;
  }

  public void setPizza(Pizza pizza) {
    this.pizza = pizza;
  }
}
