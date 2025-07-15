package org.lessons.java.crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private SpecialOfferRepository specialOfferRepository;

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public String index(Model model) {

    List<Pizza> pizzas = pizzaService.findAll();

    model.addAttribute("pizzas", pizzas);

    return "pizzas/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    Pizza pizza = pizzaService.getById(id);

    model.addAttribute("pizza", pizza);

    return "pizzas/show";
  }

  @GetMapping("/search")
  public String searchByTitle(@RequestParam String name, Model model) {

    List<Pizza> pizzas = pizzaService.findByName(name);

    model.addAttribute("pizzas", pizzas);
    model.addAttribute("name", name);

    return "pizzas/index";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("pizza", new Pizza());
    model.addAttribute("ingredients", ingredientRepository.findAll());

    return "pizzas/create";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      return "pizzas/create";
    }

    pizzaService.create(pizzaForm);
    return "redirect:/pizzas";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("pizza", repository.findById(id).get());
    model.addAttribute("ingredients", ingredientRepository.findAll());

    return "pizzas/edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("pizza") Pizza pizzaForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("ingredients", ingredientRepository.findAll());
      return "pizzas/edit";
    }

    pizzaService.update(pizzaForm);
    return "redirect:/pizzas";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Pizza pizza = pizzaService.getById(id);

    for (SpecialOffer specialOfferToDelete : pizza.getSpecialOffers()) {
      specialOfferRepository.delete(specialOfferToDelete);
    }

    pizzaService.deleteById(id);
    return "redirect:/pizzas";
  }

  @GetMapping("/{id}/discount")
  public String specialOffer(@PathVariable Integer id, Model model) {

    SpecialOffer offer = new SpecialOffer();
    offer.setPizza(pizzaService.getById(id));

    model.addAttribute("specialOffer", offer);

    return "/specialOffers/create-or-edit";
  }
}
