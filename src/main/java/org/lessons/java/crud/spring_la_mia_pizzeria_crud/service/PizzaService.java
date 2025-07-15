package org.lessons.java.crud.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Autowired
  private SpecialOfferRepository specialOfferRepository;

  public List<Pizza> findAll() {
    return pizzaRepository.findAll();
  }

  public List<Pizza> findAllSortedByName() {
    return pizzaRepository.findAll(Sort.by("name"));
  }

  public Pizza getById(Integer id) {

    return pizzaRepository.findById(id).get();
  }

  public Optional<Pizza> findById(Integer id) {
    return pizzaRepository.findById(id);
  }

  public List<Pizza> findByName(String name) {

    return pizzaRepository.findByNameContainingIgnoreCase(name);
  }

  public Pizza create(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public Pizza update(Pizza pizza) {
    return pizzaRepository.save(pizza);
  }

  public void delete(Pizza pizza) {

    for (SpecialOffer specialOfferToDelete : pizza.getSpecialOffers()) {
      specialOfferRepository.delete(specialOfferToDelete);
    }

    pizzaRepository.delete(pizza);
  }

  public void deleteById(Integer id) {

    Pizza pizza = getById(id);

    for (SpecialOffer specialOfferToDelete : pizza.getSpecialOffers()) {
      specialOfferRepository.delete(specialOfferToDelete);
    }

    pizzaRepository.delete(pizza);
  }
}
