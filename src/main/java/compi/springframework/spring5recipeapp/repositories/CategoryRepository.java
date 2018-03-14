package compi.springframework.spring5recipeapp.repositories;

import compi.springframework.spring5recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {


}
