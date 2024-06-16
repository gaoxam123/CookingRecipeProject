package com.cooking.cookingRecipes.controller.recipe;

import com.cooking.cookingRecipes.service.recipe.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("recipe")
public class RecipeController {
    private final RecipeServiceImpl recipeServiceImpl;
    @Autowired
    public RecipeController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }
    @GetMapping("/")
    public String home(){
        return "recipe/index";
    }
    @PostMapping("/edit_recipes")
    public String editRecipes(){
        return "recipe/edit";
    }
    @PostMapping("/new_recipes")
    public String newRecipe(){
        return "recipe/new";
    }
    @GetMapping("/show_recipes")
    public String showRecipes(){
        return "recipe/show";
    }


}
