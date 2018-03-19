package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.CategoryDTO;
import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.NotesDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

public class DtoToCategoryTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";

    DtoToCategory converter;

    @Before
    public void setUp() {
        converter = new DtoToCategory();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryDTO()));
    }

    @Test
    public void convert() {
        //given
        CategoryDTO dto = new CategoryDTO();
        dto.setId(ID_VALUE);
        dto.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(dto);

        //then
        assertNotNull(category);
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}