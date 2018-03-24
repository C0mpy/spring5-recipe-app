package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.converters.request.DtoToIngredient;
import compi.springframework.spring5recipeapp.converters.response.IngredientToDto;
import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceImplTest {

    IngredientServiceImpl ingredientService;

    @Mock
    IngredientToDto ingredientToDto;

    @Mock
    DtoToIngredient dtoToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        ingredientService = new IngredientServiceImpl(ingredientToDto, dtoToIngredient,
                recipeRepository, unitOfMeasureRepository);
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

    @Test
    public void saveTest() {
        UnitOfMeasureDTO uomDto = new UnitOfMeasureDTO();
        uomDto.setId(1L);

        IngredientDTO dto = new IngredientDTO();
        dto.setId(1L);
        dto.setDescription("Description");
        dto.setAmount(BigDecimal.valueOf(11));
        dto.setRecipeId(1L);
        dto.setUnitOfMeasure(uomDto);

        Ingredient mockIngredient = new Ingredient();
        mockIngredient.setId(1L);

        Recipe mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        mockRecipe.addIngredient(mockIngredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(mockRecipe));

        UnitOfMeasure mockUom = new UnitOfMeasure();
        mockUom.setId(1L);

        when(unitOfMeasureRepository.findById(anyLong())).thenReturn(Optional.of(mockUom));
        when(recipeRepository.save(any())).thenReturn(mockRecipe);
        when(ingredientToDto.convert(any())).thenReturn(dto);

        IngredientDTO savedIngredientDTO = ingredientService.saveIngredientDto(dto);

        assertEquals(savedIngredientDTO.getId(), dto.getId());
        verify(recipeRepository, times(1)).findById(any());
        verify(unitOfMeasureRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());


    }

    @Test
    public void deleteTest() {
        Recipe recipe = new Recipe();

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setRecipe(recipe);

        recipe.setId(1L);
        recipe.addIngredient(ingredient);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ingredientService.deleteByRecipeIdAndIngredientId(1L, 1L);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());
    }

}
