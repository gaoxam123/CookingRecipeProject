package com.cooking.cookingRecipes.service.recipe;

import com.cooking.cookingRecipes.dao.RecipeRepository;
import com.cooking.cookingRecipes.entity.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements  RecipeService{

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Optional<Recipe> getRecipeById(long id) {
        return recipeRepository.findById(id);
    }
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    public void addRecipe(Recipe recipe) {
        Optional<Recipe> recipeOptional=recipeRepository.findById(recipe.getID());
        if(recipeOptional.isPresent()) {
            return;
        }
        recipeRepository.save(recipe);
    }
    public void deleteRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new IllegalStateException("Recipe not found");
        }
        recipeRepository.deleteById(id);
    }
    @Transactional
    public void updateRecipe(Recipe recipe) {
        Recipe recipeToUpdate = recipeRepository.findById(recipe.getID()).orElseThrow();
        recipeToUpdate.setCategory(recipe.getCategory());
        recipeToUpdate.setImage(recipe.getImage());
        recipeToUpdate.setPrice(recipeToUpdate.getPrice());
        recipeToUpdate.setIngredients(recipe.getIngredients());
        recipeToUpdate.setDifficultyLevel(recipe.getDifficultyLevel());
        recipeRepository.save(recipeToUpdate);
    }

}
