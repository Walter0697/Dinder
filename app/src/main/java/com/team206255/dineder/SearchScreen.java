package com.team206255.dineder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class SearchScreen extends Fragment{

    ListView searchList;
    RecipeFilter searchFilter = new RecipeFilter();
    RecipeList recipeList = new RecipeList();

    CustomeAdapter customeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_search_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        ImageView searchIcon = (ImageView) view.findViewById(R.id.SearchIcon);
        Bitmap searchImage = ImageProcessor.scaleImage(R.drawable.search_icon, 0.1f);
        searchIcon.setImageBitmap(searchImage);

        customeAdapter = new CustomeAdapter(getContext(), recipeList, R.layout.search_list_detail, InfoDefine.ListType.SEARCH_LIST, getActivity());
        searchList = (ListView) view.findViewById(R.id.searchList);
        searchList.getLayoutParams().height = (int)(metrics.heightPixels * 0.7);
        searchList.setAdapter(customeAdapter);

        Button searchButton = (Button) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent filterScreen = new Intent(getContext(), FilterScreen.class);
                getActivity().startActivityForResult(filterScreen, InfoDefine.REQUEST_FOR_SEARCH);
            }
        });

        return view;
    }

    public void setUpRecipeList()
    {
        //request for the recipes again!
        searchList.invalidate();
        ((BaseAdapter)searchList.getAdapter()).notifyDataSetChanged();
    }
}
