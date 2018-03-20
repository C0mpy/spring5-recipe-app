package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToDto ingredientToDto;
    private final RecipeRepository recipeRepository;

    @Autowired
    public IngredientServiceImpl(IngredientToDto ingredientToDto, RecipeRepository recipeRepository) {
        this.ingredientToDto = ingredientToDto;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientDTO findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()) {
            log.error("Recipe not found! id=" + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientDTO> ingredientOptional = recipe.getIngredients().stream()
                .filter(ing -> ing.getId().equals(ingredientId))
                .map(ing -> ingredientToDto.convert(ing))
                .findFirst();

        if(!ingredientOptional.isPresent()) {
            log.error("Ingredient not found!");
        }

        System.out.println(ingredientOptional.get().getId());

        return ingredientOptional.get();
    }
}
