package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.request.DtoToIngredient;
import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToDto ingredientToDto;
    private final DtoToIngredient dtoToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IngredientServiceImpl(IngredientToDto ingredientToDto, DtoToIngredient dtoToIngredient,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToDto = ingredientToDto;
        this.dtoToIngredient = dtoToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

    @Override
    public IngredientDTO saveIngredientDto(IngredientDTO ingredientDTO) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId());
        if(!recipeOptional.isPresent()) {
            log.error("IngredientServiceImpl.saveIngredientDto recipe not found id=" + ingredientDTO.getRecipeId());
            return new IngredientDTO();
        }
        else {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional =
                    recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId() == ingredientDTO.getId())
                    .findFirst();

            log.warn(ingredientDTO.toString());

            if(ingredientOptional.isPresent()) {

                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientDTO.getDescription());
                ingredient.setAmount(ingredientDTO.getAmount());
                ingredient.setUom(unitOfMeasureRepository.findById(ingredientDTO.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                recipe.addIngredient(dtoToIngredient.convert(ingredientDTO));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToDto.convert(
                    savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId() == ingredientDTO.getId())
                    .findFirst()
                    .get());
        }

    }
}
