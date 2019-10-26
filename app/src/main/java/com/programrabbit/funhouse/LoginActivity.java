package com.programrabbit.funhouse;

import androidx.appcompat.app.AppCompatActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.programrabbit.funhouse.Openfire.XMPP;

public class LoginActivity extends AppCompatActivity {


    TextView tv_brand;
    TextView tv_login;


    TextView tv_forgot_pass;
    Button btn_login;
    Button btn_signup;
    Button btn_facebook;

    EditText et_username2;
    EditText et_password2;


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


        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        btn_facebook = findViewById(R.id.btn_fb);

        et_username2 = findViewById(R.id.et_username2);
        et_password2 = findViewById(R.id.et_password2);


        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(LoginActivity.this, TestMenuActivity.class);
                //startActivity(i);



                final String username = et_username2.getText().toString();
                final String password = et_password2.getText().toString();

                int val = 0;

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "Username must not be empty!",Toast.LENGTH_SHORT).show();
                    val++;
                }
                if(password.length()<=5){
                    Toast.makeText(getApplicationContext(), "Password must be greater than 5 digits",Toast.LENGTH_SHORT).show();
                    val++;
                }

                if(username.length()>30){
                    Toast.makeText(getApplicationContext(), "Username is very long!",Toast.LENGTH_SHORT).show();
                    val++;
                }

                if(val>0)
                    return;

                final Context mContext = LoginActivity.this;

                new Thread(new Runnable() {
                    public void run() {

                        final XMPP myxmpp = new XMPP(mContext);
                        //Log.d("test", "test");
                        try{
                            myxmpp.loginUser(username, password);
                        }catch (Exception e){
                            Log.e("ERROR", e.toString());
                        }
                    }
                }).start();



            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });


        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
