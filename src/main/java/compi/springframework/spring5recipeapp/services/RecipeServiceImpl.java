package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.request.DtoToRecipe;
import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.converters.response.RecipeToDto;
import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final DtoToRecipe dtoToRecipe;
    private final RecipeToDto recipeToDto;
    private final IngredientToDto ingredientToDto;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, DtoToRecipe dtoToRecipe,
                             RecipeToDto recipeToDto, IngredientToDto ingredientToDto) {
        this.recipeRepository = recipeRepository;
        this.dtoToRecipe = dtoToRecipe;
        this.recipeToDto = recipeToDto;
        this.ingredientToDto = ingredientToDto;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> recipeSet.add(recipe));
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }

        return recipeOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);

    }

    @Override
    @Transactional
    public RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO) {
        Recipe detachedRecipe = dtoToRecipe.convert(recipeDTO);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        return recipeToDto.convert(savedRecipe);

    }

    @Override
    public RecipeDTO findRecipeDTO(Long id) {
        Recipe recipe = findById(id);
        return recipeToDto.convert(recipe);
    }

}
