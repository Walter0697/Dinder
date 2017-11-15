# Team-206255
The project for 3004
##### Project Name : ~~Dineder~~  Dinder

### Quick guide:
##### Codes inside:
- ./app/src/main/java/com/team206255/dineder/
##### Assets inside:
- ./app/src/main/res/drawable/

### Fun features:
##### Recipe Information Screen:
- You can scroll the recipe instruction by volume key
- In case you are cooking, washing dishes and your hand is wet
- Hard to use touch screen in that situation
- that way you can use volume key to scroll up and down

### library include:
##### Picasso : 'com.squareup.picasso.picasso:2.5.2'
- Use for processing the image from internet 
##### Gson : 'com.google.code.gson:gson:2.7'
- Use for parsing object into json so that it can be used inside shared preference
##### Volley : 'com.android.volley:volley:1.0.0'
- Use for requesting the http request from the api

### Pattern and Style:
#### Pattern:
- Singleton pattern
- Facade pattern
- Adapter pattern
- Iterator pattern
#### Style:
- Client and Server between the app and the api
- Object-oriented 

### Files include:

##### Android related(Adapter/custom widgets):
###### CalendarAdapter.java
- for list view in calendar screen
###### CustomeAdapter.java
- for list view in like list and search screen
###### StringAdapter.java
- for list view that only contain one string such as ingredients and cuisine

###### ExpandedListView.java
- copied from stackoverflow
- Use for expanding the list view
###### NonSwipeableViewPager.java
- copied from stackoverflow
- Use for preventing the view pager to swipe so that funcitonarlities won't be duplicated between the drag and drop features and the view pager.
###### DragContainer.java
- copied from stackoverflow
- to control the alpha of the drag shader so that it can simulate dragging effect for the app
###### InputFilterMinMax.java
- copied from stackoverflow
- to control the maximum value and minimum value for text input like duration and calorie

##### Functional classes:
###### ImageProcessor.java
- For processing the image such as resizing and cropping to circle
###### InfoDefine.java
- defining the value, such as list of ingredients, good for keeping useful values in the center.
###### CallbackHelper.java
- Interface for helping call back function
###### UpdateCallBack.java
- Interface for updating layout after call back function

##### Layout related classes:
###### SplashActivity.java
- For starting screen

###### MainActivity.java
- Contain three classes and a view pager, so that user can change pages in the bottom bar
- Three classes include : MainScreen, SearchScreen, and CalendarScreen
###### MainScreen.java
- The main feature of the app, swiping recipes.
- Include two drawers, filter and list
###### ListDrawerHandler.java
- To handle everything we need inside the likelist
###### FilterDrawerHandler.java
- To handle everything we need inside the filter
- can go to advance filter setting 
###### FilterScreen.java
- An advance filter setting screen
###### CalendarScreen.java
- calendar for the saved recipes
###### SearchScreen.java
- searching the recipes according to ingredients or cuisines
###### CalendarChoice.java
- to save the recipes to the calendar
###### ImageViewScreen.java
- to browse the image of the recipe
###### SettingScreen.java
- changing the setting of the app

##### Storage type classes:
###### UserInformation.java
- To keep all the information in the center so that we can have a better architecture.
###### CalendarStorage.java
- to store the recipes inside the calendar
###### RecipeFilter.java
- to store the filter information inside this class
###### RecipeList.java
- to store a list of recipes, like liked list and search list
###### RecipeChoice.java
- to store the first n(now it is 5) recipes that will show up in the main screen
###### UserPreference.java
- to store the user preference
###### Recipe.java
- to store all the information of the recipes
###### RandomRecipeGenerator.java
- to generate a random recipe from the API
###### GetRequestURLGenerate.java
- to generate url for get request

##### Images/Icons from:
- https://www.flaticon.com/authors/smashicons
- https://www.flaticon.com/authors/freepik
- https://www.flaticon.com/authors/eight-black-dots
- from https://www.flaticon.com
- Font :
- https://www.dafont.com/grandstander.font
