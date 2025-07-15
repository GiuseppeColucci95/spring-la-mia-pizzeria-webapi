package org.lessons.java.crud.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

  // ! VARIABLES
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 50, message = "name must be of maximum 50 characters")
  @NotBlank(message = "name must not be null or empty")
  private String name;

  @Lob
  @NotBlank(message = "description must not be null")
  private String description;

  @NotBlank(message = "image url must not be null or empty")
  @Column(name = "image_url")
  private String imageUrl;

  @NotNull(message = "price must not be null")
  @DecimalMin("1")
  private BigDecimal price;

  // ! ONE TO MANY RELATIONS
  @OneToMany(mappedBy = "pizza")
  private List<SpecialOffer> specialOffers;

  // ! MANY TO MANY RELATIONS
  @ManyToMany
  @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "ingredient_id"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
  private List<Ingredient> ingredients;

  // ! GETTERS AND SETTERS
  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public List<SpecialOffer> getSpecialOffers() {
    return specialOffers;
  }

  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setSpecialOffers(List<SpecialOffer> specialOffers) {
    this.specialOffers = specialOffers;
  }

  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

}
