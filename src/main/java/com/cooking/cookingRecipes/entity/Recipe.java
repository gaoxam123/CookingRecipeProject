package com.cooking.cookingRecipes.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Recipe {
    @Id
    @SequenceGenerator(name="recipe_sequence", sequenceName ="recipe_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_sequence")
    private Long ID;
    private String title;

    //????
    private String ingredients;
    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column (nullable = false)
    private int price;
    private String image;
    //private List<Long> ReviewID;
    //private User owner;


    public Recipe(Long ID, String title, String ingredients, DifficultyLevel difficultyLevel, Category category, int price, String image) {
        this.ID = ID;
        this.title = title;
        this.ingredients = ingredients;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public Recipe() {
    }

    public Recipe(String title, String ingredients, DifficultyLevel difficultyLevel, Category category, int price) {
        this.title = title;
        this.ingredients = ingredients;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
