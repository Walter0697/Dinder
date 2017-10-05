package com.team206255.dineder;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class SearchScreen extends Fragment{

    ListView searchList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance)
    {
        View view = inflater.inflate(R.layout.activity_search_screen, container, false);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();

        ImageView searchIcon = (ImageView) view.findViewById(R.id.SearchIcon);
        Bitmap searchImage = ImageProcessor.scaleImage(metrics, getResources(), R.drawable.search, 0.1f);
        searchIcon.setImageBitmap(searchImage);

        //CustomeAdapter customeAdapter = new CustomeAdapter(getContext(), )
        searchList = (ListView) view.findViewById(R.id.searchListView);
        searchList.getLayoutParams().height = (int)(metrics.heightPixels * 0.7);

        return view;
    }

}
