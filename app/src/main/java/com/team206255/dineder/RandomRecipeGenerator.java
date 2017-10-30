package com.team206255.dineder;

import java.util.Random;
import java.util.ArrayList;

/**
 * Created by walter on 2017-10-03.
 */

public class RandomRecipeGenerator {
    Random rand = new Random();

    ArrayList<Recipe> list = new ArrayList<>();

    String[] APsteps = {"Preheat oven to 425 degrees F (220 degrees C). " ,
            "Melt the butter in a saucepan. Stir in flour to form a paste. " ,
            "Add water, white sugar and brown sugar, and bring to a boil." ,
            "Reduce temperature and let simmer.","Place the bottom crust in your pan." ,
            "Fill with apples, mounded slightly. Cover with a lattice work crust. " ,
            "Gently pour the sugar and butter liquid over the crust. " ,
            "Pour slowly so that it does not run off.","Bake 15 minutes in the preheated oven. " ,
            "Reduce the temperature to 350 degrees F (175 degrees C). " ,
            "Continue baking for 35 to 45 minutes, until apples are soft."};

    String[] APingredients = {"Crust",
            "1/2 cup unsalted butter",
            "3 tbs flour",
            "1/4 cup water",
            "1/2 cup white sugar",
            "1/2 cup brown sugar",
            "8 Apples"};

    String[] CCsteps = new String[]{"Preheat oven to 350 degrees F (175 degrees C)." +
            "Grease and flour two nine inch round pans.","In a large bowl," +
            "stir together the sugar, flour, cocoa, baking powder, baking soda and salt. " +
            "Add the eggs, milk, oil and vanilla, mix for 2 minutes on medium speed of mixer." +
            "Stir in the boiling water last. Batter will be thin." +
            "Pour evenly into the prepared pans.","Bake 30 to 35 minutes in the preheated oven," +
            "until the cake tests done with a toothpick. " +
            "Cool in the pans for 10 minutes, then remove to a wire rack to cool completely."};
    String[] CCingredients = new String [] {"1 3/4 cups flour",
            "3/4 cup cocoa",
            "1 1/2 ts baking powder",
            "1 1/2 ts baking soda",
            "1 ts salt",
            "1 cup milk",
            "2 ts vanilla"};

    String[] DHHWsteps = new String [] {"Preheat an outdoor grill for medium heat and lightly oil grate.",
            "Wash the wings well and pat dry with paper towel. " +
                    "Season the meat with cayenne, salt, and pepper.","Cook the chicken" +
            " wings on preheated grill until cooked through and juices run clear," +
            " 20 to 30 minutes depending on the size of the wings." +
            "Brush the wings liberally using 1/2 cup of honey while they are cooking.","Melt" +
            "the butter, pour into a large bowl and mix in the remaining 1/2 cup of honey and hot sauce." +
            "Remove the wings from the grill and immediately toss them in the hot honey butter sauce to coat." +
            "Serve the wings 'wet' or return them to the grill for 1 minute per side to set the sauce."};
    String[] DHHWingredients = new String [] {"2 pounds chicken",
            "1 ts cayenne",
            "salt and ground pepper",
            "1 cup honey",
            "1/2 cup butter",
            "1/2 cup hot sauce"};

    String[] GPsteps = new String [] {"Bring chicken broth to a boil. " +
            "Add potatoes and cook until tender but still firm," +
            " about 15 minutes; drain, reserving broth.","Stir in garlic, cream and 1 to 2 tablespoons broth;" +
            " mash until creamy. Blend in sour cream, chives, salt and butter." +
            " Heat through and serve."};

    String[] GPingredients = new String[]{"chicken broth",
            "potatoes",
            "garlic",
            "cream",
            "sour cream",
            "salt",
            "butter"};

    String[] BLTDsteps = new String[]{"Place bacon in a large, deep skillet." +
            " Cook over medium high heat until evenly brown. Drain on paper towels.","In a medium bowl," +
            " combine mayonnaise and sour cream. Crumble bacon into the sour cream and mayonnaise mixture." +
            " Mix in tomatoes just before serving."};
    String[] BLTDingredients = new String[]{"mayonnaise","sour cream","tomato","chicken"};




    //(String name, String[] steps, String[] ingredients, int difficulty, float duration, float calorie)
    public RandomRecipeGenerator() {
        Recipe recipe1 = new Recipe("Chocolate Cake","https://spoonacular.com/recipeImages/Ramen-Noodle-Coleslaw-556177.jpg", APsteps, APingredients,3,60.f,100.f);
        Recipe recipe2 = new Recipe("Chocolate Cake","https://spoonacular.com/recipeImages/Grandmas-Apple-Crisp-645152.jpg",CCsteps,CCingredients,3,60.f,100.f);
        Recipe recipe3 = new Recipe("Detroit Hot Honey Wings","https://spoonacular.com/recipeImages/Quick-Apple-Ginger-Pie-657563.jpg",DHHWsteps,DHHWingredients,2,30.f,60.f);
        Recipe recipe4 = new Recipe("Garlic Potatoes","https://spoonacular.com/recipeImages/Cinnamon-Sugar-Fried-Apples-639487.jpg",GPsteps,GPingredients,5,20.f,80.f);
        Recipe recipe5 = new Recipe("BLT Dip","https://spoonacular.com/recipeImages/Fresh-Apple-Cake-With-Caramel-Sauce-643426.jpg",BLTDsteps,BLTDingredients,1,25.f,30.f);

        list.add(recipe1);
        list.add(recipe2);
        list.add(recipe3);
        list.add(recipe4);
        list.add(recipe5);
    }

    public Recipe getRandomRecipe()
    {
        //return new Recipe(rand.nextInt(5));
        return list.get(rand.nextInt(list.size()));
    }
}
