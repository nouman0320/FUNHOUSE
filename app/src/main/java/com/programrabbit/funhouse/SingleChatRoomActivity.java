package com.programrabbit.funhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class SingleChatRoomActivity extends AppCompatActivity {




    Toolbar chat_tb;


    Boolean isMessageInProgress = false;

    TextView tv_title;


    ConstraintLayout space;
    ConstraintLayout cl_message;
    ConstraintLayout space2;


    TextView tv_start;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.user_list:
                //addSomething();
                return true;
            case R.id.leave_room:
                //startSettings();
                return true;
            case R.id.lock_room:
                //startSettings();
                //nothing
                return true;
            case R.id.change_background:
                //startsSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat_room);
        getSupportActionBar().hide();

        Typeface lu_b = Typeface.createFromAsset(getAssets(),  "fonts/lucida_sans_demibold_roman.ttf");

        chat_tb = findViewById(R.id.chat_toolbar);
        //chat_tb.setTitle("Pakistan");
        chat_tb.setNavigationIcon(R.drawable.ic_left_arrow);
        chat_tb.inflateMenu(R.menu.chatroom_menu);

        tv_title = findViewById(R.id.toolbar_title);
        tv_title.setText("Pakistan");
        tv_title.setTypeface(lu_b);


        space = findViewById(R.id.space);
        space2 = findViewById(R.id.space_msg);
        cl_message = findViewById(R.id.cl_message);

        tv_start = findViewById(R.id.tv_start);
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableMessageBox(!isMessageInProgress);
            }
        });




        enableMessageBox(false);
    }

    void enableMessageBox(boolean val){
        isMessageInProgress = val;

        if(!isMessageInProgress){
            cl_message.setVisibility(View.GONE);
            space2.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
            tv_start.setText("Start a Conversation.");
        } else {
            cl_message.setVisibility(View.VISIBLE);
            space2.setVisibility(View.VISIBLE);
            space.setVisibility(View.GONE);
            tv_start.setText("Hide Message Box.");
        }
    }
}
