package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientToDtoTest {

    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    @Mock
    UnitOfMeasureToDto uomToDto;

    IngredientToDto converter;

    @Before
    public void setUp() {
        converter = new IngredientToDto(uomToDto);
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        ingredient.setUom(unitOfMeasure);

        //configure mock
        UnitOfMeasureDTO uomDTO = new UnitOfMeasureDTO();
        uomDTO.setId(UOM_ID);
        when(uomToDto.convert(isA(UnitOfMeasure.class))).thenReturn(uomDTO);

        //when
        IngredientDTO dto = converter.convert(ingredient);

        //then
        assertNotNull(dto);
        assertEquals(ID_VALUE, dto.getId());
        assertEquals(DESCRIPTION, dto.getDescription());
        assertEquals(AMOUNT, dto.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uom = new UnitOfMeasure();

        //configure mock
        when(uomToDto.convert(isNull())).thenReturn(null);

        //when
        IngredientDTO dto = converter.convert(ingredient);

        //then
        assertNotNull(dto);
        assertNull(dto.getUnitOfMeasure());
        assertEquals(ID_VALUE, dto.getId());
        assertEquals(AMOUNT, dto.getAmount());
        assertEquals(DESCRIPTION, dto.getDescription());
    }

}