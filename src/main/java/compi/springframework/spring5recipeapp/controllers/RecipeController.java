package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodType;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/recipe/{id}/show", method = RequestMethod.GET)
    public String showById(@PathVariable String id, Model model) {

        log.info("RecipeController.showById called with: id=" + id);
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipeForm";
    }

    @RequestMapping(value = "/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeDTO(Long.valueOf(id)));
        return "recipe/recipeForm";
    }


    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDTO dto) {

        RecipeDTO savedDto = recipeService.saveRecipeDTO(dto);
        return "redirect:/recipe/" + savedDto.getId() + "/show";
    }

}
