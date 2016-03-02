package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.CategoryFragment;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.category_activity, new CategoryFragment()).commit();
        }
    }

}
