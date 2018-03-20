package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;

public interface IngredientService {

    IngredientDTO findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
