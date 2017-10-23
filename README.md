# Team-206255
The project for 3004
Project Name : Dineder

### library include:
Picasso : 'com.squareup.picasso.picasso:2.5.2'
- Use for processing the image from internet

### Files include:

##### Android related(Adapter/custom widgets):
###### CalendarAdapter.java
- for list view in calendar screen
CustomeAdapter.java
- for list view in like list and search screen
StringAdapter.java
- for list view that only contain one string such as ingredients and cuisine

ExpandedListView.java
- copied from stackoverflow
- Use for expanding the list view
NonSwipeableViewPager.java
- copied from stackoverflow
- Use for preventing the view pager to swipe so that funcitonarlities won't be duplicated between the drag and drop features and the view pager.

##### Functional classes:
ImageProcessor.java
- For processing the image such as resizing and cropping to circle
InfoDefine.java
- defining the value, such as list of ingredients, good for keeping useful values in the center.

##### Layout related classes:
MainActivity.java
- Contain three classes and a view pager, so that user can change pages in the bottom bar
- Three classes include : MainScreen, SearchScreen, and CalendarScreen
MainScreen.java
- The main feature of the app, swiping recipes.
- Include two drawers, filter and list
ListDrawerHandler.java
- To handle everything we need inside the likelist
FilterDrawerHandler.java
- To handle everything we need inside the filter
- can go to advance filter setting 
FilterScreen.java
- An advance filter setting screen
CalendarScreen.java
- calendar for the saved recipes
SearchScreen.java
- searching the recipes according to ingredients or cuisines
CalendarChoice.java
- to save the recipes to the calendar

##### Storage type classes:
CalendarStorage.java
- to store the recipes inside the calendar
RecipeFilter.java
- to store the filter information inside this class
RecipeList.java
- to store a list of recipes, like liked list and search list
RecipeChoice.java
- to store the first n(now it is 5) recipes that will show up in the main screen
Recipe.java
- to store all the information of the recipes
RandomRecipeGenerator.java
- to generate a random recipe from the API

##### Classes that will be deleted soon:
JsonFactory
- similar usage can be found in randomRecipeGenerator

##### Classes that will be added in the future:
Singleton.java
- To keep all the information in the center so that we can have a better architecture.
