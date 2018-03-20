package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    void deleteById(Long id);
    RecipeDTO saveRecipeDTO(RecipeDTO command);
    RecipeDTO findRecipeDTO(Long id);
}
