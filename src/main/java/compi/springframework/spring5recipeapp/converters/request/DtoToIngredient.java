package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DtoToIngredient implements Converter<IngredientDTO, Ingredient> {

    private final DtoToUnitOfMeasure dtoToUnitOfMeasure;

    @Autowired
    public DtoToIngredient(DtoToUnitOfMeasure dtoToUnitOfMeasure) {
        this.dtoToUnitOfMeasure = dtoToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientDTO source) {

        if(source == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(dtoToUnitOfMeasure.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
