package org.lessons.java.crud.spring_la_mia_pizzeria_crud.repository;

import org.lessons.java.crud.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
