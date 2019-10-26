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

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    TextView tv_brand;
    TextView tv_login;


    EditText et_username;
    EditText et_password;
    EditText et_email;
    EditText et_phone;

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

        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_phone = findViewById(R.id.et_mobile);


        btn_signup = findViewById(R.id.btn_signup_2);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    public void run() {
                        //Log.d("test", "test");
                        XMPP myxmpp = new XMPP(getApplicationContext());
                        try {

                            String username = et_username.getText().toString();
                            String password = et_password.getText().toString();
                            String email = et_email.getText().toString();
                            String phone = et_phone.getText().toString();


                            int val = 0;

                            if(TextUtils.isEmpty(username)){
                                Toast.makeText(getApplicationContext(), "Username must not be empty!",Toast.LENGTH_SHORT).show();
                                val++;
                            }
                            if(password.length()<=5){
                                Toast.makeText(getApplicationContext(), "Password must be greater than 5 digits",Toast.LENGTH_SHORT).show();
                                val++;
                            }

                            if(TextUtils.isEmpty(email)){
                                Toast.makeText(getApplicationContext(), "Email must not be empty!",Toast.LENGTH_SHORT).show();
                                val++;
                            }

                            if(TextUtils.isEmpty(phone)){
                                Toast.makeText(getApplicationContext(), "Phone number must not be empty!",Toast.LENGTH_SHORT).show();
                                val++;
                            }

                            if(!isEmailValid(email)){
                                Toast.makeText(getApplicationContext(), "Email must be valid",Toast.LENGTH_SHORT).show();
                                val++;
                            }

                            if(username.length()>30){
                                Toast.makeText(getApplicationContext(), "Username is very long!",Toast.LENGTH_SHORT).show();
                                val++;
                            }

                            if(val>0)
                                return;


                            myxmpp.registerUser(username, password, email, phone);
                        } catch (Exception e) {
                            Log.e("test", e.toString());
                            Toast.makeText(getApplicationContext(), "Unable to register new user",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();



            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
