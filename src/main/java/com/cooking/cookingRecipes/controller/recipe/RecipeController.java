package com.cooking.cookingRecipes.controller.recipe;

import com.cooking.cookingRecipes.service.recipe.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RecipeController {
    private final RecipeServiceImpl recipeServiceImpl;
    @Autowired
    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }
}
