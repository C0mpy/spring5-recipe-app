package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    IngredientToDto ingredientToDto;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        ingredientService = new IngredientServiceImpl(ingredientToDto, recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientIdPassingTest() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ing1 = new Ingredient();
        ing1.setId(1L);

        Ingredient ing2 = new Ingredient();
        ing2.setId(2L);

        Ingredient ing3 = new Ingredient();
        ing3.setId(3L);

        recipe.addIngredient(ing1);
        recipe.addIngredient(ing2);
        recipe.addIngredient(ing3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(1L);
        ingredientDTO.setRecipeId(recipe.getId());
        when(ingredientToDto.convert(any())).thenReturn(ingredientDTO);

        //then
        IngredientDTO result = ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(1L), Long.valueOf(1L));
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals(Long.valueOf(1L), result.getRecipeId());
    }


}
