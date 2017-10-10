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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.team206255.dineder.InfoDefine.*;

public class FavouriteScreen extends Fragment {

    ListView savedView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_favourite_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        ImageView favouriteIcon = (ImageView) view.findViewById(R.id.favouriteIcon);
        Bitmap favouriteImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.star, 0.1f);
        favouriteIcon.setImageBitmap(favouriteImage);

        //CustomeAdapter customeAdapter = new CustomeAdapter(getContext(), MainActivity.saveList, R.layout.saved_list_detail, ListType.SAVED_LIST);
        /*savedView = (ListView) view.findViewById(R.id.saveListView);
        savedView.getLayoutParams().height = (int)(metrics.heightPixels * 0.7);
        savedView.setAdapter(customeAdapter);

        savedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent recipeIntent = new Intent(getActivity().getApplicationContext(), RecipeInformation.class);
                recipeIntent.putExtra("RECIPE", MainActivity.saveList.getRecipe(i));
                startActivity(recipeIntent);
            }
        });*/


        return view;
    }
}
