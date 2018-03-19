package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DtoToRecipe implements Converter<RecipeDTO, Recipe> {

    private final DtoToNotes dtoToNotes;
    private final DtoToCategory dtoToCategory;
    private final DtoToIngredient dtoToIngredient;

    @Autowired
    public DtoToRecipe(DtoToNotes dtoToNotes, DtoToCategory dtoToCategory, DtoToIngredient dtoToIngredient) {
        this.dtoToNotes = dtoToNotes;
        this.dtoToCategory = dtoToCategory;
        this.dtoToIngredient = dtoToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeDTO source) {

        if(source == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setSource(source.getSource());
        recipe.setServings(source.getServings());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());

        recipe.setNotes(dtoToNotes.convert(source.getNotes()));

        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(dtoToCategory.convert(category)));
        }

        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> recipe.addIngredient(dtoToIngredient.convert(ingredient)));
        }

        return recipe;
    }
}
