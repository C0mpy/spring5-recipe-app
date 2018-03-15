package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.model.Category;
import compi.springframework.spring5recipeapp.model.UnitOfMeasure;
import compi.springframework.spring5recipeapp.repositories.CategoryRepository;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasure) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasure;
    }

    @RequestMapping({"/", "", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UOM Id is: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
