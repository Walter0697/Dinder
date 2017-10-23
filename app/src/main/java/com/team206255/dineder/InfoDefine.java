package com.team206255.dineder;

/**
 * Created by walter on 2017-09-29.
 */

public class InfoDefine {
    //static final int minDuration = 5;
    static final int maxDuration = 100;
    //static final int minCalorie = 5;
    static final int maxCalorie = 100;

    public enum Cuisine {
        ALL, HONGKONG, CHINESE, JAPANESE, KOREAN,
        INDIAN, FRENCH, AMERICAN, ITALIAN, MEXICAN
    }

    public enum Ingredient {
        SUGAR, SALT, OIL, CHICKEN, BEEF, PEPPER, EGG
    }

    static final int REQUEST_FOR_FILTER = 0;
    static final int REQUEST_FOR_CALENDAR = 1;
    static final int REQUEST_FOR_SEARCH = 2;

    public enum ListType {
        LIKED_LIST, SEARCH_LIST, INGREDIENT_BOX, CUISINE_BOX, INGREDIENT_LIST, STEPS
    }
}
