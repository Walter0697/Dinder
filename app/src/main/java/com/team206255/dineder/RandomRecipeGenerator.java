package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by walter on 2017-10-03.
 */

public class RandomRecipeGenerator {
    static Random rand = new Random();

    //setting up the queue of the http request
    static RequestQueue queue;
    static int MYSOCKET_TIMEOUT_MS = 5000;

    static String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=false&number=1";
    static String APIKey = "4WH5shG2ShmshNOHlTLcGuvisDXkp1FGULPjsnb2ImIUrqcMW6";
    static String currentAPI = APIKey;

    //setting up context
    static Context mcontext;

    static ArrayList<Recipe> list = new ArrayList<>();

    static String[] APsteps = {"Preheat oven to 425 degrees F (220 degrees C). " ,
            "Melt the butter in a saucepan. Stir in flour to form a paste. " ,
            "Add water, white sugar and brown sugar, and bring to a boil." ,
            "Reduce temperature and let simmer.","Place the bottom crust in your pan." ,
            "Fill with apples, mounded slightly. Cover with a lattice work crust. " ,
            "Gently pour the sugar and butter liquid over the crust. " ,
            "Pour slowly so that it does not run off.","Bake 15 minutes in the preheated oven. " ,
            "Reduce the temperature to 350 degrees F (175 degrees C). " ,
            "Continue baking for 35 to 45 minutes, until apples are soft."};
    static String[] APingredients = {"Crust",
            "1/2 cup unsalted butter",
            "3 tbs flour",
            "1/4 cup water",
            "1/2 cup white sugar",
            "1/2 cup brown sugar",
            "8 Apples"};

    static String[] CCsteps = new String[]{"Preheat oven to 350 degrees F (175 degrees C)." +
            "Grease and flour two nine inch round pans.","In a large bowl," +
            "stir together the sugar, flour, cocoa, baking powder, baking soda and salt. " +
            "Add the eggs, milk, oil and vanilla, mix for 2 minutes on medium speed of mixer." +
            "Stir in the boiling water last. Batter will be thin." +
            "Pour evenly into the prepared pans.","Bake 30 to 35 minutes in the preheated oven," +
            "until the cake tests done with a toothpick. " +
            "Cool in the pans for 10 minutes, then remove to a wire rack to cool completely."};
    static String[] CCingredients = new String [] {"1 3/4 cups flour",
            "3/4 cup cocoa",
            "1 1/2 ts baking powder",
            "1 1/2 ts baking soda",
            "1 ts salt",
            "1 cup milk",
            "2 ts vanilla"};

    static String[] DHHWsteps = new String [] {"Preheat an outdoor grill for medium heat and lightly oil grate.",
            "Wash the wings well and pat dry with paper towel. " +
                    "Season the meat with cayenne, salt, and pepper.","Cook the chicken" +
            " wings on preheated grill until cooked through and juices run clear," +
            " 20 to 30 minutes depending on the size of the wings." +
            "Brush the wings liberally using 1/2 cup of honey while they are cooking.","Melt" +
            "the butter, pour into a large bowl and mix in the remaining 1/2 cup of honey and hot sauce." +
            "Remove the wings from the grill and immediately toss them in the hot honey butter sauce to coat." +
            "Serve the wings 'wet' or return them to the grill for 1 minute per side to set the sauce."};
    static String[] DHHWingredients = new String [] {"2 pounds chicken",
            "1 ts cayenne",
            "salt and ground pepper",
            "1 cup honey",
            "1/2 cup butter",
            "1/2 cup hot sauce"};

    static String[] GPsteps = new String [] {"Bring chicken broth to a boil. " +
            "Add potatoes and cook until tender but still firm," +
            " about 15 minutes; drain, reserving broth.","Stir in garlic, cream and 1 to 2 tablespoons broth;" +
            " mash until creamy. Blend in sour cream, chives, salt and butter." +
            " Heat through and serve."};
    static String[] GPingredients = new String[]{"chicken broth",
            "potatoes",
            "garlic",
            "cream",
            "sour cream",
            "salt",
            "butter"};

    static String[] BLTDsteps = new String[]{"Place bacon in a large, deep skillet." +
            " Cook over medium high heat until evenly brown. Drain on paper towels.","In a medium bowl," +
            " combine mayonnaise and sour cream. Crumble bacon into the sour cream and mayonnaise mixture." +
            " Mix in tomatoes just before serving."};
    static String[] BLTDingredients = new String[]{"mayonnaise","sour cream","tomato","chicken"};

    static String[] LLCsteps = new String[]{"Make Cake:  Preheat oven to 350°F. Butter and flour three 9 inch cake pans." +
            " In a small bowl, whisk together flour, baking powder, and salt. In a large bowl, beat together butter" +
            " and sugar with an electric mixer until light and fluffy, about 3 minutes (you can also use a stand mixer" +
            " fitted with the paddle attachment on medium speed). Beat in vanilla. Beat in eggs, one at a time. Beat" +
            " in half of the flour mixture. Beat in milk. Beat in remaining flour mixture." +
            " Beat in lemon juice and lemon zest.","Pour batter into prepared cake pans. Bake until lightly golden and" +
            " a cake tester inserted into middle of the cake comes out clean, about 20 minutes. Let cake cool in pans for" +
            " fifteen minutes, then turn out onto a wire rack.","Meanwhile, make the frosting. In a large bowl, beat together" +
            " butter, 6 cups of confectioner's sugar, milk, lemon juice, and lemon zest with a wooden spoon until incorporated." +
            " Continue to beat for 3 minutes, or until frosting begins to get fluffy. At this point, taste the frosting. If desired," +
            " add remaining two cups of confectioner's sugar in 1/2 cup increments, tasting for preferred sweetness." +
            " Continue to beat frosting until thick and fluffy, about 3 minutes.","To assemble: In a small bowl, break up raspberries" +
            " with a fork until they have a spreadable consistency. Place first layer of cake on a stand or plate. Frost with icing" +
            " then cover evenly with raspberries. Place second layer of cake on top of first. Frost with icing and cover evenly with" +
            " strawberries. Place final layer of cake on top of first two and frost the top and sides evenly with remaining frosting." +
            " Decorate circumference of top of frosted cake with blackberries.\""};
    static String[] LLCingredients = new String[]{"3 cups flour","2 1/2 ts baking powder","4 ounces blackberries","4 eggs",
            "1 ts salt","1/2 cup lemon juice","2 tbs milk","6 ounces raspberries","6 ounces strawberries","2 cups sugar","2 sticks butter"};


    static String[] GELsteps = new String[]{"Cut vanilla bean lengthwise and scrape out the seeds. Discard outer bean.Place egg yolks," +
            " vanilla seeds, and sugar in the bowl of a stand mixer fitted with the whisk beater. Beat on high speed for 10 minutes." +
            "Slowly add cream and condensed milk and beat for 7 minutes.Slowly add rum and beat for 3 minutes.Pour egg liqueur" +
            " in decorative bottles or containers. Seal and refrigerate for up to 3 weeks."};
    static String[] GELingredients = new String[]{"10 egg yolks","1 cup whipping cream","1 light rum","1 1/2 cups sugar",
            " 3/4 condensed milk","1 vanilla bean"};

    static String[] FBsteps = new String[]{"Combine all dry ingredients in mixer bowl. Stir on low until well-combined." +
            "In a separate bowl, combine all wet ingredients, including coconut oil. Slowly drizzle wet ingredients" +
            " into dry ingredients with mixer on low.Turn the granola mixture out onto a heavily-sprayed rimmed sheet" +
            " pan. Bake in heated oven at 350° for 25 to 30 minutes, or until golden brown. If using a dark sheet pan," +
            " you may need less time, around 20 to 25 minutes. Stir twice while baking.Turn warm baked granola onto a" +
            " cool sheet pan lined with parchment paper.  Spread granola out evenly all the way to the edges using a " +
            "stiff metal spatula. Press the granola mixture into the rimmed baking sheet by placing a second rimmed " +
            "baking sheet on top of the one holding the granola and pressing down firmly.Refrigerate overnight to set." +
            "  Cut in to square and wrap squares with strips of parchment paper." +
            "  Store in resealable bag and keep refrigerate."};
    static String[] FBingredients = new String[]{"1 cup amber agave","1/4 cup brown sugar","1 tbs cinnamon","1/4 cup coconut oil",
            "1 cup dried fruit","1 ts ginger","1/4 cup juice","1/2 ts nutmeg"};

    static String[] PCMsteps = new String[]{"Preheat the oven to 375 degrees and spray a large loaf pan with cooking spray." +
            "In a large skillet, add the butter and the onions and bell peppers, mushrooms, salt and pepper.Let brown" +
            " for 1-2 minutes before stirring, then let brown for another 1-2 minutes before stirring again.Let cool for" +
            " five minutes.In a large bowl add the ground beef, ketchup,Worcestershire sauce, eggs, panko breadcrumbs and" +
            " the cooled vegetable mixture.Add half the mixture to your loaf pan then add half of the cheese to the middle" +
            " of the pan in a 2 inch wide section (I cut the slices into thirds for the right width).Cover with the rest of" +
            " the meat and form into a rounded top loaf shape.Cook for 40 minutes, then pull out of the oven, cover with " +
            "remaining cheese and put back in. Cook for 15-20 minutes, then let rest for 10 minutes before cutting."};
    static String[] PCMingredients = new String[]{"2 tbs butter","1 onion","1 green pepper","8 ounces mushrooms",
            "1/2 ts salt","1/2 ts pepper","2 eggs"};

    static String[] PPingredients = new String[]{"2 tbs brown sugar","2 tbs butter","1 cup cream","2 tsp maple syrup",
            "8 ounces pecans","1 pizza crust","1/2 cup pie filling"};

    static String[] GCsteps = new String[]{"1 Heat a frying pan to medium high heat. For each slice of bread, butter one side" +
            " and place slice butter side down on the hot pan.2 Add a layer of cheese to one of the slices. As the bread" +
            " begins to toast just slightly, and the cheese begins to soften, spread the heated sauerkraut over the slice" +
            " of bread with cheese. Using a spatula, flip the cheese-less bread slice over on top of the slice with cheese" +
            " and sauerkraut.3 After 30 sec or so, check to see if the cheese is just beginning to melt. If it is, flip the whole sandwich over onto the other side." +
            " Toast a minute more or less on that side until the cheese has melted, but isn't runny. Remove the sandwich from the pan. Slice in half."};
    static String[] GCingredients = new String[]{"Cheese","2 ts butter","Rye bread","Sauerkraut"};

    static String[] CRsteps = new String[]{"Preheat the oven to 425 degrees.In a bowl stir together the yogurt, cream cheese, green onions," +
            " garlic, ginger, soy sauce, sugar, and crab meat.Lay the wontons out and fill each with 1 tablespoon crab mixture. Wet" +
            " the edges of the wontons and fold closed.Place in a single layer on a parchment lined baking sheet. Spray the crab rangoons" +
            " with cooking spray to help crisp up the top.Bake for about 10 minutes. The flip over and cook for an additional 5 minutes or" +
            " until crisp.Variation: If you want a more classic, bubbly outside you can pan fry the wontons in 2 tbsp of vegetable oil." +
            " Just made sure to adjust the points level. (Add .75 points plus per piece to recipe)"};
    static String[] CRingredients = new String[]{"garlic","4 green onions","ginger","crab meat","greek yogurt","cream cheese"};

    static String[] Rsteps = new String[]{"For the salsa: Heat oil in a medium saucepan over medium heat. When it shimmers, add tomatillos," +
            " onions, jalapeno, and garlic, and cook until tomatillos and onions are softened, about 7 minutes. Transfer mixture to a blender," +
            " add cilantro, vinegar, and salt, and puree until sauce is smooth. (Yields about 2 cups salsa.) For the eggs: Bring at least 3" +
            " inches of water to a simmer in a medium pot and stir in vinegar. Break each egg into a separate small cup. Fill a large bowl" +
            " with warm water and set aside. Gently slide eggs into the simmering water, 1 at a time. Cook until whites are just set, about 2" +
            " to 3 minutes. Lift eggs out of the water with a slotted spoon. Place in a bowl of warm water to keep warm while finishing the dish. " +
            "For the tortillas: Heat oil in a large heavy bottomed pan over medium-high heat to 350 degrees F. When oil is ready, add tortillas 1" +
            " at a time and fry until golden brown and crisp on both sides, about 1 minute. Transfer to a paper-towel-lined plate and season immediately with salt.                          To serve: For each serving, place down 2 corn tortillas, place 2 eggs on top, drizzle with salsa, and sprinkle with cheese." +
            " If desired, top with a dollop of sour cream and slices of avocado. Serve immediately."};
    static String[] Ringredients = new String[]{"corn tortillas","sour cream","avacado","eggs","cilantro","garlic","salt","onion"};

    static String[] DSsteps = new String[]{"Put bread slices on flat surface. Arrange pepper, mesclun, red onion, tomatoes, pickles and cucumbers in readied piles." +
            " Starting with the bottom layer, spread mayonnaise on top side of bread, and layer with cucumbers, 1 cheese slice at an angle and strips of pickles" +
            " lengthwise on bread. Place second slice of bread on filling, spread top side with mustard and layer with sliced red onion and “bologna.”Place third" +
            " slice of bread on filling, and spread top side with mayonnaise and relish and layer with red pepper slices, lettuce and 2 slices “ham.”Place fourth" +
            " slice of bread on filling, and spread top side with mustard and layer with tomato slices, 1 slice cheese at an angle and 8 slices “pepperoni.”Spread" +
            " underside of fifth slice of bread with mayonnaise, and place on top of stack." +
            " Skewer sandwich layers together with a 12-inch-long bamboo skewer or metal skewer, and serve."};
    static String[] DSingredients = new String[]{"bologna","cucumbers","musturd","pickles","relish","red onion","bread"};


    //(String name, String URL, String[] steps, String[] ingredients, int difficulty, float duration, float calorie)
    public RandomRecipeGenerator() {}

    public static void setUpQueue(Context context)
    {
        queue = new Volley().newRequestQueue(context);
        mcontext = context;

    }

    public static void setupDummy()
    {
        Recipe recipe1 = new Recipe(1, "Apple Pie","http://41feasts.com/wp-content/uploads/2012/10/Baked-Alaska.jpg", APsteps, APingredients,3,60.f,100.f);
        Recipe recipe2 = new Recipe(2, "Chocolate Cake","https://spoonacular.com/recipeImages/Grandmas-Apple-Crisp-645152.jpg",CCsteps,CCingredients,3,60.f,100.f);
        Recipe recipe3 = new Recipe(3, "Detroit Hot Honey Wings","https://spoonacular.com/recipeImages/Quick-Apple-Ginger-Pie-657563.jpg",DHHWsteps,DHHWingredients,2,30.f,60.f);
        Recipe recipe4 = new Recipe(4, "Garlic Potatoes","https://spoonacular.com/recipeImages/Cinnamon-Sugar-Fried-Apples-639487.jpg",GPsteps,GPingredients,5,20.f,80.f);
        Recipe recipe5 = new Recipe(5, "BLT Dip","https://spoonacular.com/recipeImages/Fresh-Apple-Cake-With-Caramel-Sauce-643426.jpg",BLTDsteps,BLTDingredients,1,25.f,30.f);
        Recipe recipe6 = new Recipe(6, "Lemon Layer Cake", "https://spoonacular.com/recipeImages/Fresh-Apple-Cake-With-Caramel-Sauce-643426.jpg",LLCsteps,LLCingredients,3,80.f,100.f);
        Recipe recipe7 = new Recipe(7, "German Egg Liqueue","https://spoonacular.com/recipeImages/621568-556x370.jpg",GELsteps,GELingredients,2,25.f,70.f);
        Recipe recipe8 = new Recipe(8, "Fruit and Nut Bar","https://spoonacular.com/recipeImages/588330-556x370.jpg",FBsteps,FBingredients,3,45.f,60.f);
        Recipe recipe9 = new Recipe(9, "Philly Cheesesteak Meatloaf","https://spoonacular.com/recipeImages/924481-556x370.jpg",PCMsteps,PCMingredients,4,45.f,120.f);
        Recipe recipe10 = new Recipe(10, "Pumpkin Pie","https://spoonacular.com/recipeImages/655525-556x370.jpg",APsteps,PPingredients,6,45.f,100.f);
        Recipe recipe11 = new Recipe(11, "Grilled Cheese","https://spoonacular.com/recipeImages/247051-556x370.jpeg",GCsteps,GCingredients,4,20.f,70.f);
        Recipe recipe12 = new Recipe(12, "Crab Rangoon","https://spoonacular.com/recipeImages/510597-556x370.jpg",CRsteps,CRingredients,8,45.f,100.f);
        Recipe recipe13 = new Recipe(13, "Rancheros","https://spoonacular.com/recipeImages/749525-556x370.jpeg",Rsteps,Ringredients,7,25.f,50.f);
        Recipe recipe14 = new Recipe(14, "Dagwood Sandwich","https://spoonacular.com/recipeImages/760918-556x370.jpg",DSsteps,DSingredients,5,30.f,70.f);

        list.add(recipe1);
        list.add(recipe2);
        list.add(recipe3);
        list.add(recipe4);
        list.add(recipe5);
        list.add(recipe6);
        list.add(recipe7);
        list.add(recipe8);
        list.add(recipe9);
        list.add(recipe10);
        list.add(recipe11);
        list.add(recipe12);
        list.add(recipe13);
        list.add(recipe14);
    }

    public static Recipe getRandomRecipe()
    {
        //return new Recipe(rand.nextInt(5));
        return list.get(rand.nextInt(list.size()));
    }

    public static void getSimilarRecipeAPI()
    {
        setURL(GetRequestURLGenerate.getSimilarURL());
        Log.d("url", url);
        getJSONArray(new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.d("result", result.toString());
            }

            @Override
            public void onSuccess(Bitmap result) {

            }
        });
    }

    public static void getNutrientsRecipeAPI()
    {
        setURL(GetRequestURLGenerate.getNutrientsURL());
        Log.d("url", url);
        getJSONArray(new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.d("jsonForNutrients", result.toString());
                //-->use Recipe(int id, String name, String pictureUrl)
                //****************DO THIS FIRST TO SEE IF IT WILL WORK***************

                String title = result.optString("title");
                String URL = result.optString("image");
                int id = result.optInt("id");

                Recipe recipe = new Recipe(id,title,URL);

                UserInformation.getInstance().getRecipeChoice().addRecipe(recipe);
            }

            @Override
            public void onSuccess(Bitmap result) {

            }
        });
    }

    //list information
    //-> 0. Swiping one
    //-> 1. Liked List
    //-> 2. CalendarStorage (for the current day)
    public static void setToRecipeAPI(int id, final int list, final int position, final UpdateCallBack updateCallBack)
    {
        setURL(GetRequestURLGenerate.getFoodinfoURL(id));
        Log.d("request", "list"+list+",position"+position);
        //return for invalid number
        if (list > 2 || list < 0) return;
        getJSONObject(new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.d("jsonForInfo", result.toString());
                //*******************************************TRY THIS ONE
                //retrieveInformation(String[] ingredients, String[] steps, float duration,
                //float calorie, float fat, float protein, float carbs, int healthScore)

                int duration = result.optInt("readyInMinutes");
                float dur = (float)duration;
                int health = result.optInt("healthScore");

                Log.d("duration", String.valueOf(dur));
                Log.d("health", String.valueOf(health));

                JSONArray ingredients = result.optJSONArray("extendedIngredients");

                String[] ingred = new String[ingredients.length()];
                for(int k = 0; k < ingredients.length(); k++)
                {
                    JSONObject indaviualIngred = ingredients.optJSONObject(k);
                    ingred[k] = indaviualIngred.optString("originalString");
                }

                Log.d("ingredients", String.valueOf(ingred[0]));

                JSONObject nutrition = result.optJSONObject("nutrition");
                JSONArray nutrients = nutrition.optJSONArray("nutrients");
                JSONObject calObj = nutrients.optJSONObject(0);
                double calories = calObj.optDouble("amount");
                float cal = (float)calories;

                Log.d("calories", String.valueOf(cal));

                JSONObject fatObj = nutrients.optJSONObject(1);
                double ft = fatObj.optDouble("amount");
                float fat = (float)ft;

                Log.d("FAT", String.valueOf(fat));

                JSONObject proObj = nutrients.optJSONObject(7);
                double pro = proObj.optDouble("amount");
                float protein = (float)pro;

                JSONObject carbObj = nutrients.optJSONObject(3);
                double carb = proObj.optDouble("amount");
                float carbs = (float)carb;

                Log.d("PROTEIN", String.valueOf(protein));
                Log.d("CARBS", String.valueOf(carbs));

                JSONArray analyzedInstructions = result.optJSONArray("analyzedInstructions");
                JSONObject instrucObj = analyzedInstructions.optJSONObject(0);
                JSONArray steps = instrucObj.optJSONArray("steps");
                String[] instruc = new String[steps.length()];
                for(int k = 0; k < steps.length(); k++)
                {
                    JSONObject individualStep = steps.optJSONObject(k);
                    instruc[k] = individualStep.optString("step");
                }

                Log.d("INSTRUCTIONS", instruc[0]);

                //retrieveInformation(ingred, instruc, dur,cal,fat,protein,carbs,health);


                switch(list)
                {
                    case 0:
                        UserInformation.getInstance().getRecipeChoice().getChoiceRecipe().retrieveInformation(ingred, instruc, dur,cal,fat,protein,carbs,health);
                        updateCallBack.update();
                        break;
                    case 1:
                        UserInformation.getInstance().getRecipeList().getRecipe(position).retrieveInformation(ingred, instruc, dur,cal,fat,protein,carbs,health);
                        updateCallBack.update();
                        break;
                    case 2:
                        UserInformation.getInstance().getCalendarStorage().getRecipe(position).retrieveInformation(ingred, instruc, dur,cal,fat,protein,carbs,health);
                        updateCallBack.update();
                        break;
                }
            }

            @Override
            public void onSuccess(Bitmap result) {

            }
        });
    }

    //to get the json file and parse them into Recipe class
    public static void getRandomRecipeAPI()
    {
        setURL(GetRequestURLGenerate.getRandomURL());
        getJSONObject(new CallbackHelper() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.d("randomJSON", result.toString());
                JSONArray recipes = result.optJSONArray("recipes");
                JSONObject rec = recipes.optJSONObject(0);
                String title = rec.optString("title");
                String URL = rec.optString("image");
                //String instructions = rec.optString("instructions");
                int duration = rec.optInt("readyInMinutes");
                int health = rec.optInt("healthScore");
                int id = rec.optInt("id");
                int serving = rec.optInt("servings");
                JSONArray ingredients = rec.optJSONArray("extendedIngredients");

                String[] ingred = new String[ingredients.length()];
                String[] instructionsArray = new String[100];

                instructionsArray[0] = rec.optString("instructions");

                for(int k = 0; k < ingredients.length(); k++)
                {
                    JSONObject indaviualIngred = ingredients.optJSONObject(k);
                    ingred[k] = indaviualIngred.optString("originalString");
                }

                //Log.d("TITLE",title);
                //Log.d("id", String.valueOf(id));
                //Log.d("serving", String.valueOf(serving));
                //Log.d("URL",URL);
                //Log.d("INSTRUCTIONS",instructionsArray[0]);
                //Log.d("ingredients",ingred[0]);
                //Log.d("duration", String.valueOf(duration));
                //Log.d("health", String.valueOf(health));

                // int id, String name, String pictureUrl, String[] steps, String[] ingredients, int difficulty, float duration, float calorie

                float dur = (float)duration;
                float healthScore = (float)health;

                //serving number is being sent in as difficulty for now
                //Recipe recipe = new Recipe(id,title,URL,instructionsArray,ingred,serving, dur, healthScore);
                Recipe recipe = new Recipe(id, title, URL);
                //later should be change to adding to recipe choice
                //UserInformation.getInstance().getCalendarStorage().addRecipe(new Date(), recipe, 0);
                UserInformation.getInstance().getRecipeChoice().addRecipe(recipe);
            }

            @Override
            public void onSuccess(Bitmap result){
            }
        });
    }

    public static void setURL(String input)
    {
        url = input;
    }

    /**************************************************************************/
    /******* Function that get json from api, using callback to set value *****/
    /******* Don't have to change it, url can be set in different method ******/
    /******* api key can be found in the very top, don't have to change *******/
    /**************************************************************************/
    private static void getJSONObject(final CallbackHelper callback)
    {
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("X-Mashape-Key", currentAPI);
                params.put("Accept", "application/json");
                return params;
            }
        };
        Log.d("request", "starting");
        queue.add(getRequest);
    }

    private static void getJSONArray(final CallbackHelper callbackHelper)
    {
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Random rand = new Random();
                            callbackHelper.onSuccess(response.getJSONObject(rand.nextInt(response.length())));
                            //callbackHelper.onSuccess(response.getJSONObject(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("X-Mashape-Key", currentAPI);
                params.put("Accept", "application/json");
                return params;
            }
        };

        getRequest.setRetryPolicy(new DefaultRetryPolicy(
            MYSOCKET_TIMEOUT_MS,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        Log.d("request", "starting");
        queue.add(getRequest);
    }
}
