package compi.springframework.spring5recipeapp.services;

import compi.springframework.spring5recipeapp.model.Recipe;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> recipeSet.add(recipe));
        return recipeSet;
    }
}
