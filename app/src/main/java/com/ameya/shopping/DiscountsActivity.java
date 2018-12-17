package com.ameya.shopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DiscountsActivity extends AppCompatActivity {

    private String TAG = DiscountsActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate");
        setContentView(R.layout.activity_discounts);

    }
}
