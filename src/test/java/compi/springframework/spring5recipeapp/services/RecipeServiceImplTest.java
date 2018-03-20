package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.request.DtoToRecipe;
import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.converters.response.RecipeToDto;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    DtoToRecipe dtoToRecipe;

    @Mock
    RecipeToDto recipeToDto;

    @Mock
    IngredientToDto ingredientToDto;

    @Before
    public void setUp() {
        recipeService = new RecipeServiceImpl(recipeRepository, dtoToRecipe, recipeToDto, ingredientToDto);
    }

    @Test
    public void getRecipesTest() {

        HashSet<Recipe> recipesData =  new HashSet<>();
        Recipe recipe = new Recipe();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        Assert.assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteRecipeById() {

        Long idToDelete = new Long(2);
        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

}