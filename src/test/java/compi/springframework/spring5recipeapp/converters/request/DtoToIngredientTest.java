package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DtoToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    @Mock
    DtoToUnitOfMeasure dtoToUnitOfMeasure;

    DtoToIngredient converter;

    @Before
    public void setUp() {
        converter = new DtoToIngredient(dtoToUnitOfMeasure);
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientDTO()));
    }

    @Test
    public void convert() {
        //given
        IngredientDTO dto = new IngredientDTO();
        dto.setId(ID_VALUE);
        dto.setDescription(DESCRIPTION);
        dto.setAmount(AMOUNT);

        UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
        unitOfMeasureDTO.setId(UOM_ID);
        dto.setUnitOfMeasure(unitOfMeasureDTO);

        //configure mock
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        when(dtoToUnitOfMeasure.convert(isA(UnitOfMeasureDTO.class))).thenReturn(uom);

        //when
        Ingredient ingredient = converter.convert(dto);

        //then
        assertNotNull(ingredient);
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientDTO command = new IngredientDTO();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureDTO uomDto = new UnitOfMeasureDTO();

        //configure mock
        when(dtoToUnitOfMeasure.convert(isNull())).thenReturn(null);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

}