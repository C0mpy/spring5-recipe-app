package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.converters.request.DtoToCategory;
import compi.springframework.spring5recipeapp.dtos.CategoryDTO;
import compi.springframework.spring5recipeapp.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToDtoTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";

    CategoryToDto converter;

    @Before
    public void setUp() {
        converter = new CategoryToDto();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryDTO dto= converter.convert(category);

        //then
        assertNotNull(dto);
        assertEquals(ID_VALUE, dto.getId());
        assertEquals(DESCRIPTION, dto.getDescription());
    }

}