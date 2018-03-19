package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToUnitOfMeasureTest {


    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    DtoToUnitOfMeasure converter;

    @Before
    public void setUp() {
        converter = new DtoToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureDTO()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasureDTO dto = new UnitOfMeasureDTO();
        dto.setId(LONG_VALUE);
        dto.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(dto);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }

}