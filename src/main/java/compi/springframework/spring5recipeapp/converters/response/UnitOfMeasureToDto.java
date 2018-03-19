package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToDto implements Converter<UnitOfMeasure, UnitOfMeasureDTO> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureDTO convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }
        UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
        unitOfMeasureDTO.setId(source.getId());
        unitOfMeasureDTO.setDescription(source.getDescription());
        return unitOfMeasureDTO;
    }
}
