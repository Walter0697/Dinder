package com.team206255.dineder;

import android.graphics.Bitmap;

import org.json.JSONObject;

//concept from https://stackoverflow.com/questions/28120029/how-can-i-return-value-from-function-onresponse-of-volley

public interface CallbackHelper {
    void onSuccess(JSONObject result);
    void onSuccess(Bitmap result);
}
