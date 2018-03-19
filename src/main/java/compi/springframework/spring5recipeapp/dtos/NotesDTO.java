package compi.springframework.spring5recipeapp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesDTO {
    private Long id;
    private String recipeNotes;
}
