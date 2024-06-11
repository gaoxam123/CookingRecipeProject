package com.cooking.cookingRecipes.dao;

import com.cooking.cookingRecipes.entity.recipe.Recipe;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface RecipeRepository extends Repository<Recipe, Long> {
}
