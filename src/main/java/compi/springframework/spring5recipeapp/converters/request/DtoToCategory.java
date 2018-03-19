package compi.springframework.spring5recipeapp.converters.request;

import compi.springframework.spring5recipeapp.dtos.CategoryDTO;
import compi.springframework.spring5recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DtoToCategory implements Converter<CategoryDTO, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryDTO source) {

        if(source == null) {
            return null;
        }

        Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }

}
