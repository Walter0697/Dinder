package com.team206255.dineder;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

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
    private SeekBar carbsSeekBar;
    private EditText carbsNum;
    private SeekBar fatSeekBar;
    private EditText fatNum;
    private SeekBar proteinSeekBar;
    private EditText proteinNum;

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
        durationNum.setFilters(new InputFilter[] {new InputFilterMinMax(0, InfoDefine.maxDuration)});
        //setting up min and max range for the seekbar
        //durationSeekBar.setMin(InfoDefine.minDuration);
        durationSeekBar.setMax(InfoDefine.maxDuration);
        //setting up the on seekbar listener
        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UserInformation.getInstance().getRecipeFilter().duration = i;
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
                UserInformation.getInstance().getRecipeFilter().duration = Integer.parseInt(editable.toString());
                durationSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        ///////////////////////////////////////////////////////////////////
        //CALORIE SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for duration
        calorieSeekBar = (SeekBar) headerView.findViewById(R.id.calorieSeekBar);
        calorieNum = (EditText) headerView.findViewById(R.id.calorieNum);
        calorieNum.setFilters(new InputFilter[] {new InputFilterMinMax(0, InfoDefine.maxCalorie)});
        //setting up min and max range for the seekbar
        //calorieSeekBar.setMin(InfoDefine.minCalorie);
        calorieSeekBar.setMax(InfoDefine.maxCalorie);
        //setting up the on seekbar listener
        calorieSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UserInformation.getInstance().getRecipeFilter().calorie = i;
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
                UserInformation.getInstance().getRecipeFilter().calorie = Integer.parseInt(editable.toString());
                calorieSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        ///////////////////////////////////////////////////////////////////
        //CARBS SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for carbs
        carbsSeekBar = (SeekBar) headerView.findViewById(R.id.carbsSeekBar);
        carbsNum = (EditText) headerView.findViewById(R.id.carbsNum);
        carbsNum.setFilters(new InputFilter[] {new InputFilterMinMax(0, InfoDefine.maxCarbs)});
        //setting up min and max range for the seekbar
        carbsSeekBar.setMax(InfoDefine.maxCarbs);
        //setting up the on seekbar listener
        carbsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UserInformation.getInstance().getRecipeFilter().carbs = i;
                carbsNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //setting up the edittext on change listener
        carbsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UserInformation.getInstance().getRecipeFilter().carbs = Integer.parseInt(editable.toString());
                carbsSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });

        ///////////////////////////////////////////////////////////////////
        //FAT SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for fat
        fatSeekBar = (SeekBar) headerView.findViewById(R.id.fatSeekBar);
        fatNum = (EditText) headerView.findViewById(R.id.fatNum);
        fatNum.setFilters(new InputFilter[] {new InputFilterMinMax(0, InfoDefine.maxFat)});
        //setting up min and max range for the seekbar
        fatSeekBar.setMax(InfoDefine.maxFat);
        //setting up the on seekbar listener
        fatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UserInformation.getInstance().getRecipeFilter().fat = i;
                fatNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //setting up the edittext on change listener
        fatNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UserInformation.getInstance().getRecipeFilter().fat = Integer.parseInt(editable.toString());
                fatSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });
        ///////////////////////////////////////////////////////////////////
        //PROTEIN SEEKBAR + EDITTEXT
        //setting up the seekbar and edittext for protein
        proteinSeekBar = (SeekBar) headerView.findViewById(R.id.proteinSeekBar);
        proteinNum = (EditText) headerView.findViewById(R.id.proteinNum);
        proteinNum.setFilters(new InputFilter[] {new InputFilterMinMax(0, InfoDefine.maxProtein)});
        //setting up min and max range for the seekbar
        proteinSeekBar.setMax(InfoDefine.maxProtein);
        //setting up the on seekbar listener
        proteinSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                UserInformation.getInstance().getRecipeFilter().protein = i;
                proteinNum.setText(Integer.toString(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //setting up the edittext on change listener
        proteinNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                UserInformation.getInstance().getRecipeFilter().protein = Integer.parseInt(editable.toString());
                proteinSeekBar.setProgress(Integer.parseInt(editable.toString()));
            }
        });
        setValueByFilter(UserInformation.getInstance().getRecipeFilter());
    }

    public void setValueByFilter(RecipeFilter filter)
    {
        //getting the duration value from the filter
        durationNum.setText(Integer.toString((int)filter.duration));
        durationSeekBar.setProgress((int)filter.duration);

        //getting the calorie value from the filter
        calorieNum.setText(Integer.toString((int)filter.calorie));
        calorieSeekBar.setProgress((int)filter.calorie);

        //getting the carbs value from the filter
        carbsNum.setText(Integer.toString((int)filter.carbs));
        carbsSeekBar.setProgress((int)filter.carbs);

        //getting the fat vaule from the filter
        fatNum.setText(Integer.toString((int)filter.fat));
        fatSeekBar.setProgress((int)filter.fat);

        //getting the protein value from the filter
        proteinNum.setText(Integer.toString((int)filter.protein));
        proteinSeekBar.setProgress((int)filter.protein);
    }
}
