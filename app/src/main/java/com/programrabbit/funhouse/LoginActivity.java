package com.programrabbit.funhouse;

import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    TextView tv_brand;
    TextView tv_login;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        tv_brand = findViewById(R.id.tv_brand);
        tv_login = findViewById(R.id.tv_login);

        Typeface lu_b = Typeface.createFromAsset(getAssets(),  "fonts/lucida_sans_demibold_roman.ttf");

        tv_brand.setTypeface(lu_b);
        tv_login.setTypeface(lu_b);

    }
}
