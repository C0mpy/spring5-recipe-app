package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.services.IngredientService;
import compi.springframework.spring5recipeapp.services.RecipeService;
import compi.springframework.spring5recipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    IngredientController controller;

    @Before
    public void setUp() {
        controller = new IngredientController(recipeService, unitOfMeasureService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getIngredientsTest() throws Exception {

        RecipeDTO dto = new RecipeDTO();
        dto.setId(1L);
        when(recipeService.findRecipeDTO(anyLong())).thenReturn(dto);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void showIngredientTest() throws Exception {

        IngredientDTO dto = new IngredientDTO();
        dto.setId(1L);
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(dto);

        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

    }

    @Test
    public void updateIngredientFormTest() throws Exception {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(1L);

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientDTO);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<UnitOfMeasureDTO>());

        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));

    }

    @Test
    public void saveOrUpdateTest() throws Exception {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(3L);
        ingredientDTO.setRecipeId(2L);

        when(ingredientService.saveIngredientDto(any())).thenReturn(ingredientDTO);

        mockMvc.perform(post("/recipe/2/ingredient/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string"))

                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
    }


}
