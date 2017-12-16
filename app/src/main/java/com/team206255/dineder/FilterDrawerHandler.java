package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Created by walter on 2017-09-29.
 */

public class FilterDrawerHandler {

    private Context context;

    //set the widgets here so that we can use for all function
    private SwitchCompat switchButton;

    private Spinner calorieSpinner;
    private Spinner fatSpinner;

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
        Bitmap drawerImage = ImageProcessor.scaleImage(R.drawable.filterdrawer, 0.1f);
        drawerIcon.setImageBitmap(drawerImage);

        ////////////////////////////
        //CALORIE SPINNER
        calorieSpinner = (Spinner) headerView.findViewById(R.id.calorieSpinner);
        String[] calorieChoice = {"1000", "800", "500", "300", "100"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, calorieChoice);
        calorieSpinner.setAdapter(adapter);
        calorieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                UserInformation.getInstance().getRecipeFilter().calorie_set_by_spinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ////////////////////////////
        //FAT SPINNER
        fatSpinner = (Spinner) headerView.findViewById(R.id.fatSpinner);
        String[] fatChoice = {"100", "80", "60", "40", "20"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, fatChoice);
        fatSpinner.setAdapter(adapter2);
        fatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 UserInformation.getInstance().getRecipeFilter().fat_set_by_spinner(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ///////////////////////////////////////////////////////////////////
        //FOR CHRISTMAS ONLY!!!!!!!
        switchButton = (SwitchCompat) headerView.findViewById(R.id.switchbutton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                UserInformation.getInstance().getRecipeFilter().festivalFilter = b;
            }
        });

        setValueByFilter(UserInformation.getInstance().getRecipeFilter());
    }

    //set up the filter
    public void setValueByFilter(RecipeFilter filter)
    {
        //set up the spinner
        calorieSpinner.setSelection(filter.calorie_spinner_position());
        fatSpinner.setSelection(filter.fat_spinner_position());

        //getting the switch value from the filter
        switchButton.setChecked(UserInformation.getInstance().getRecipeFilter().festivalFilter);
    }
}
