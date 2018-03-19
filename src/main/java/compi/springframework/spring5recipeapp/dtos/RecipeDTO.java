package compi.springframework.spring5recipeapp.dtos;

import compi.springframework.spring5recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesDTO notes;
    private Set<CategoryDTO> categories = new HashSet<>();

    public void addCategory(CategoryDTO dto) {
        categories.add(dto);
    }

    public void addIngredient(IngredientDTO dto) {
        ingredients.add(dto);
    }
}
