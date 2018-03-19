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

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {

        log.debug("RecipeController.showById id=" + id);
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {

        log.debug("RecipeController.new page display");
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {

        log.debug("RecipeController.update page display id=" + id);
        model.addAttribute("recipe", recipeService.findRecipeDTO(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDTO dto) {

        log.debug("RecipeController.saveOrUpdate");
        RecipeDTO savedDto = recipeService.saveRecipeDTO(dto);
        return "redirect:/recipe/" + savedDto.getId() + "/show";
    }

    @GetMapping
    @RequestMapping(value = "/recipe/{id}/delete")
    public String updateRecipe(@PathVariable String id) {

        log.debug("RecipeController.delete id=" + id);
        recipeService.deleteById(Long.parseLong(id));
        return "redirect:/";
    }

}
