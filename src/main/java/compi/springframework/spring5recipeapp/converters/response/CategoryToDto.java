package compi.springframework.spring5recipeapp.converters.response;

import compi.springframework.spring5recipeapp.dtos.CategoryDTO;
import compi.springframework.spring5recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToDto implements Converter<Category, CategoryDTO> {

    @Synchronized
    @Nullable
    @Override
    public CategoryDTO convert(Category source) {

        if(source == null) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(source.getId());
        categoryDTO.setDescription(source.getDescription());
        return  categoryDTO;
    }
}
