package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.NotesDTO;
import compi.springframework.spring5recipeapp.model.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToNotesTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NOTES = "notes";

    DtoToNotes converter;

    @Before
    public void setUp() {
        converter = new DtoToNotes();
    }

    @Test
    public void testNullParameter() {
        assertNull(converter.convert(null));

    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesDTO()));
    }

    @Test
    public void convert() {
        //given
        NotesDTO dto = new NotesDTO();
        dto.setId(ID_VALUE);
        dto.setRecipeNotes(NOTES);

        //when
        Notes notes = converter.convert(dto);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());
    }

}