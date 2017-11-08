package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Created by walter on 2017-09-29.
 */

public class FilterDrawerHandler {

    private Context context;

    //set the widgets here so that we can use for all function
    private SeekBar durationSeekBar;
    private EditText durationNum;
    private SeekBar calorieSeekBar;
    private EditText calorieNum;

    public FilterDrawerHandler(Context c)
    {
        this.context = c;
    }

    public void handleDrawerSetup(View headerView)
    {
        DisplayMetrics metrics = headerView.getResources().getDisplayMetrics();

        //expend the drawer header to the end so that it can get rid of the menu
        LinearLayout linearLayout = (LinearLayout) headerView.findViewById(R.id.filterHeader);
        LinearLayout.LayoutParams linear_params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        linear_params.height = (int)(metrics.heightPixels);
        linearLayout.setLayoutParams(linear_params);

        //setting up the icon of the drawer
        ImageView drawerIcon = (ImageView) headerView.findViewById(R.id.drawerRightIcon);
        Bitmap drawerImage = ImageProcessor.scaleImage(metrics, headerView.getResources(), R.drawable.filterdrawer, 0.1f);
        drawerIcon.setImageBitmap(drawerImage);

        ///////////////////////////////////////////////////////////////////
        //DURATION SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for duration
        durationSeekBar = (SeekBar) headerView.findViewById(R.id.durationSeekBar);
        durationNum = (EditText) headerView.findViewById(R.id.durationNum);
        durationNum.setFilters(new InputFilter[] {new InputFilterMinMax(1, InfoDefine.maxDuration)});
        //setting up min and max range for the seekbar
        //durationSeekBar.setMin(InfoDefine.minDuration);
        durationSeekBar.setMax(InfoDefine.maxDuration);
        //setting up the on seekbar listener
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Singleton.getInstance().getRecipeFilter().duration = i;
                durationNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //setting up the edittext on change listener
        durationNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Singleton.getInstance().getRecipeFilter().duration = Integer.parseInt(editable.toString());
                durationSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        ///////////////////////////////////////////////////////////////////
        //CALORIE SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for duration
        calorieSeekBar = (SeekBar) headerView.findViewById(R.id.calorieSeekBar);
        calorieNum = (EditText) headerView.findViewById(R.id.calorieNum);
        calorieNum.setFilters(new InputFilter[] {new InputFilterMinMax(1, InfoDefine.maxCalorie)});
        //setting up min and max range for the seekbar
        //calorieSeekBar.setMin(InfoDefine.minCalorie);
        calorieSeekBar.setMax(InfoDefine.maxCalorie);
        //setting up the on seekbar listener
        calorieSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Singleton.getInstance().getRecipeFilter().calorie = 1;
                calorieNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //setting up the edittext on change listener
        calorieNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Singleton.getInstance().getRecipeFilter().calorie = Integer.parseInt(editable.toString());
                calorieSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        setValueByFilter(Singleton.getInstance().getRecipeFilter());
    }

    public void setValueByFilter(RecipeFilter filter)
    {
        //getting the value from the filter
        durationNum.setText(Integer.toString((int)filter.duration));
        durationSeekBar.setProgress((int)filter.duration);

        //getting the value from the filter
        calorieNum.setText(Integer.toString((int)filter.calorie));
        calorieSeekBar.setProgress((int)filter.calorie);
    }
}
