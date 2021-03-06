package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/", "", "/index"})
    public String getIndexPage(Model model) {

        log.debug("IndexController.getIndexPage called...");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
