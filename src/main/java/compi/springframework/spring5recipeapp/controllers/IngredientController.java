package compi.springframework.spring5recipeapp.controllers;

import compi.springframework.spring5recipeapp.dtos.IngredientDTO;
import compi.springframework.spring5recipeapp.dtos.RecipeDTO;
import compi.springframework.spring5recipeapp.dtos.UnitOfMeasureDTO;
import compi.springframework.spring5recipeapp.model.Ingredient;
import compi.springframework.spring5recipeapp.services.IngredientService;
import compi.springframework.spring5recipeapp.services.RecipeService;
import compi.springframework.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, UnitOfMeasureService unitOfMeasureService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model) {

        log.debug("IngredientController.listIngredients recipeId=" + id);
        model.addAttribute("recipe", recipeService.findRecipeDTO(Long.parseLong(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredientForm(@PathVariable String recipeId, Model model) {

        log.debug("IngredientController.newIngredientForm");

        // check if recipeId is good
        RecipeDTO recipeDTO = recipeService.findRecipeDTO(Long.valueOf(recipeId));
        //todo raise expetion if recipeDTO is null

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(recipeDTO.getId());
        model.addAttribute("ingredient", ingredientDTO);

        Set<UnitOfMeasureDTO> uoms = unitOfMeasureService.listAllUoms();
        model.addAttribute("uomList", uoms);
        return "recipe/ingredient/ingredientForm";

    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId, Model model) {

        log.debug("IngredientController.showIngredient recipeId=" + recipeId + " ingredientId=" + ingredientId);
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredientForm(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model) {

        log.debug("IngredientController.updateIngredientForm recipeId=" + recipeId + " ingredientId=" + ingredientId);
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping
    @RequestMapping(value = "/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@PathVariable String recipeId,
                               @ModelAttribute IngredientDTO ingredientDTO) {

        log.debug("IngredientController.saveOrUpdate recipeId=" + recipeId);
        IngredientDTO savedDTO = ingredientService.saveIngredientDto(ingredientDTO);

        return "redirect:/recipe/" + recipeId + "/ingredient/" + savedDTO.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String delete(@PathVariable String recipeId,
                         @PathVariable String ingredientId, Model model) {

        log.debug("IngredientController.delete recipeId=" + recipeId + " ingredientId=" + ingredientId);
        ingredientService.deleteByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
