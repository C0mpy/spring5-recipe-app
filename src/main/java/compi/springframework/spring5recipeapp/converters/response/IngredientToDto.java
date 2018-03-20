package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToDto implements Converter<Ingredient, IngredientDTO> {

    private final UnitOfMeasureToDto unitOfMeasureToDto;

    @Autowired
    public IngredientToDto(UnitOfMeasureToDto unitOfMeasureToDto) {
        this.unitOfMeasureToDto = unitOfMeasureToDto;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDTO convert(Ingredient source) {

        if(source == null) {
            return null;
        }
        IngredientDTO dto = new IngredientDTO();
        dto.setId(source.getId());
        if(source.getRecipe() != null)
            dto.setRecipeId(source.getRecipe().getId());
        dto.setAmount(source.getAmount());
        dto.setDescription(source.getDescription());
        dto.setUnitOfMeasure(unitOfMeasureToDto.convert(source.getUom()));
        return dto;
    }
}
