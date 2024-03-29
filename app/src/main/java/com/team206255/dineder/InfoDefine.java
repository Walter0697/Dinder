package com.team206255.dineder;

/**
 * Created by walter on 2017-09-29.
 */

public class InfoDefine {
    //static final int minDuration = 5;
    static final int maxDuration = 1000;
    //static final int minCalorie = 5;
    static final int maxCalorie = 1000;

    static final int maxCarbs = 100;

    static final int maxFat = 100;

    static final int maxProtein = 100;

    public enum Cuisine {
        ALL, AFRICAN, CHINESE, JAPANESE, KOREAN, VIETNAMESE, THAI,
        INDIAN, BRITISH, IRISH, FRENCH, ITALIAN, MEXICAN, SPANISH,
        JEWISH, AMERICAN, CAJUN, SOUTHERN, GREEK, GERMAN, NORDIC,
        CARIBBEAN
    }

    public enum Ingredient {
        CHICKEN, CHEESE, BEEF, SALMON, TOMATOES, POTATOES,
        LETTUCE, OIL, BREAD, EGGS, FLOUR, RICE
    }

    static final int REQUEST_FOR_FILTER = 0;
    static final int REQUEST_FOR_CALENDAR = 1;
    static final int REQUEST_FOR_SEARCH = 2;

    public enum ListType {
        LIKED_LIST, SEARCH_LIST, INGREDIENT_BOX, CUISINE_BOX, INGREDIENT_LIST, STEPS
    }

    static final int NONE = 100;
    static final int HALLOWEEN = 101;
    static final int CHRISTMAS = 102;
}
