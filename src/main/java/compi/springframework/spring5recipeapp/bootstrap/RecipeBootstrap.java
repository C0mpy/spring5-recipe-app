package compi.springframework.spring5recipeapp.bootstrap;

import compi.springframework.spring5recipeapp.model.*;
import compi.springframework.spring5recipeapp.repositories.CategoryRepository;
import compi.springframework.spring5recipeapp.repositories.RecipeRepository;
import compi.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if(!eachUomOptional.isPresent())
            throw new RuntimeException("Expected UOM not found");

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category not found");

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category not found");

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.");
        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), guacRecipe, eachUom));
        guacRecipe.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(5), guacRecipe, teaspoonUom));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon jucice", new BigDecimal(2), guacRecipe, tablespoonUom));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), guacRecipe, tablespoonUom));
        guacRecipe.getIngredients().add(new Ingredient("serrano chillies, stems and seeds removed, minced", new BigDecimal(2), guacRecipe, eachUom));
        guacRecipe.getIngredients().add(new Ingredient("cilantro", new BigDecimal(2), guacRecipe, tablespoonUom));
        guacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), guacRecipe, dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), guacRecipe, eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        recipes.add(guacRecipe);

        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setCookTime(9);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        Notes tacosNotes = new Notes();
        tacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n");
        tacosNotes.setRecipe(tacosRecipe);
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.getIngredients().add(new Ingredient("ancho chilli powder", new BigDecimal(2), tacosRecipe, tablespoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), tacosRecipe, teaspoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("dried cumin", new BigDecimal(1), tacosRecipe, teaspoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), tacosRecipe, teaspoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), tacosRecipe, teaspoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("clove of garlic, chopped", new BigDecimal(1), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange, zest", new BigDecimal(1), tacosRecipe, tablespoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tacosRecipe, tablespoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), tacosRecipe, tablespoonUom));
        tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillas", new BigDecimal(8), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), tacosRecipe, cupUom));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados", new BigDecimal(2), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(.5), tacosRecipe, pintUom));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(.25), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("roughly chopped cilantro", new BigDecimal(4), tacosRecipe, eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), tacosRecipe, cupUom));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), tacosRecipe, eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacosRecipe);
        return recipes;

    }

}
