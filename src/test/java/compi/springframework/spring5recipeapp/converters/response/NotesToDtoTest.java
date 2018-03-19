package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.NotesDTO;
import compi.springframework.spring5recipeapp.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToDtoTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NOTES = "notes";

    NotesToDto converter;

    @Before
    public void setUp() {
        converter = new NotesToDto();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(NOTES);

        //when
        NotesDTO dto = converter.convert(notes);

        //then
        assertNotNull(dto);
        assertEquals(ID_VALUE, dto.getId());
        assertEquals(NOTES, dto.getRecipeNotes());
    }
}