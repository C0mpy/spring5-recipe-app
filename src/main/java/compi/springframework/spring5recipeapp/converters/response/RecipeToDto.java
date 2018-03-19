package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToDto implements Converter<Recipe, RecipeDTO> {

    private final IngredientToDto ingredientToDto;
    private final NotesToDto notesToDto;
    private final CategoryToDto categoryToDto;

    @Autowired
    public RecipeToDto(NotesToDto notesToDto, CategoryToDto categoryToDto, IngredientToDto ingredientToDto) {
        this.notesToDto = notesToDto;
        this.categoryToDto = categoryToDto;
        this.ingredientToDto = ingredientToDto;
    }

    @Override
    public RecipeDTO convert(Recipe source) {
        if(source == null) {
            return null;
        }
        RecipeDTO dto = new RecipeDTO();
        dto.setId(source.getId());
        dto.setCookTime(source.getCookTime());
        dto.setDescription(source.getDescription());
        dto.setDifficulty(source.getDifficulty());
        dto.setDirections(source.getDirections());
        dto.setPrepTime(source.getPrepTime());
        dto.setServings(source.getServings());
        dto.setSource(source.getSource());
        dto.setUrl(source.getUrl());

        if(source.getCategories() != null && source.getCategories().size() != 0) {
            source.getCategories().forEach(category ->
                dto.addCategory(categoryToDto.convert(category))
            );
        }

        if(source.getIngredients() != null && source.getIngredients().size() != 0) {
            source.getIngredients().forEach(ingredient ->
                dto.addIngredient(ingredientToDto.convert(ingredient))
            );
        }

        dto.setNotes(notesToDto.convert(source.getNotes()));

        return dto;
    }
}
