package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.NotesDTO;
import compi.springframework.spring5recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToDto implements Converter<Notes, NotesDTO> {

    @Synchronized
    @Nullable
    @Override
    public NotesDTO convert(Notes source) {

        if(source == null) {
            return null;
        }
        NotesDTO dto = new NotesDTO();
        dto.setId(source.getId());
        dto.setRecipeNotes(source.getRecipeNotes());
        return dto;
    }
}
