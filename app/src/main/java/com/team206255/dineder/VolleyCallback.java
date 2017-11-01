package com.team206255.dineder;

import org.json.JSONObject;

//concept from https://stackoverflow.com/questions/28120029/how-can-i-return-value-from-function-onresponse-of-volley

public interface VolleyCallback {
    void onSuccess(JSONObject result);
}
