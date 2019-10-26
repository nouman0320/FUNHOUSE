package com.programrabbit.funhouse;

import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {


    TextView tv_brand;
    TextView tv_login;


    Button btn_signup;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tv_brand = findViewById(R.id.tv_brand);
        tv_login = findViewById(R.id.tv_login);

        Typeface lu_b = Typeface.createFromAsset(getAssets(),  "fonts/lucida_sans_demibold_roman.ttf");

        tv_brand.setTypeface(lu_b);
        tv_login.setTypeface(lu_b);


        btn_signup = findViewById(R.id.btn_signup_2);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
