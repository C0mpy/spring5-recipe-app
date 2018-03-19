package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.NotesDTO;
import compi.springframework.spring5recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DtoToNotes implements Converter<NotesDTO, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesDTO source) {

        if(source == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
