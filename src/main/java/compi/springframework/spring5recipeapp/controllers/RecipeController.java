package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {

        log.info("RecipeController.showById called with: id=" + id);
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipeForm";
    }

    @RequestMapping(name = "recipe")
    @PostMapping
    public String saveOrUpdate(@ModelAttribute RecipeDTO dto) {

        RecipeDTO savedDto = recipeService.saveRecipeDTO(dto);
        return "redirect:/recipe/show/" + savedDto.getId();
    }
}
