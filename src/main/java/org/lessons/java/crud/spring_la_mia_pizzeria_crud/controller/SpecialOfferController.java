package org.lessons.java.crud.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/discounts")
public class SpecialOfferController {

  @Autowired
  private SpecialOfferRepository repository;

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "specialOffers/create-or-edit";
    }

    repository.save(formSpecialOffer);

    return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("specialOffer", repository.findById(id).get());
    model.addAttribute("edit", true);

    return "specialOffers/create-or-edit";
  }

  @PostMapping("/{id}/edit")
  public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer,
      BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "specialOffers/create-or-edit";
    }

    repository.save(formSpecialOffer);

    return "redirect:/pizzas/" + formSpecialOffer.getPizza().getId();
  }
}
