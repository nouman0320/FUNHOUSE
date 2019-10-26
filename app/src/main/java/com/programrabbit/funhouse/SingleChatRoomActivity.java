package com.programrabbit.funhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.programrabbit.funhouse.Adapter.ChatMessageListAdapter;
import com.programrabbit.funhouse.Model.ChatMessage;
import com.programrabbit.funhouse.Model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SingleChatRoomActivity extends AppCompatActivity {




    Toolbar chat_tb;


    Boolean isMessageInProgress = false;

    TextView tv_title;


    ConstraintLayout space;
    ConstraintLayout cl_message;
    ConstraintLayout space2;

    EditText et_message;


    TextView tv_start;

    Button btn_send;

    private RecyclerView mMessageRecycler;
    private ChatMessageListAdapter mMessageAdapter;

    ArrayList<ChatMessage> messageList;

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
        et_message = findViewById(R.id.et_message);


        tv_start = findViewById(R.id.tv_start);
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableMessageBox(!isMessageInProgress);
            }
        });

        enableMessageBox(false);


        messageList = new ArrayList<>();
        messageList.add(new ChatMessage(new User("Laura"), "Hello Steve :) How are you doing???", new Date()));
        messageList.add(new ChatMessage(new User("Steve"), "Pretty good, what about you?", new Date()));
        messageList.add(new ChatMessage(new User("Laura"), "Great, thanks for asking", new Date()));
        messageList.add(new ChatMessage(new User("Laura"), "Are you free atm?", new Date()));


        mMessageRecycler = findViewById(R.id.recyclerview_messages);
        mMessageAdapter = new ChatMessageListAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);


        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(isMessageInProgress && !TextUtils.isEmpty(et_message.getText())){
                 messageList.add(new ChatMessage(new User("Steve"), et_message.getText().toString(), new Date()));
                 et_message.setText("");
                 enableMessageBox(false);
                 et_message.clearFocus();
                 mMessageRecycler.scrollToPosition(messageList.size() - 1);
             }
            }
        });
    }

    void enableMessageBox(boolean val){
        isMessageInProgress = val;

        if(!isMessageInProgress){
            cl_message.setVisibility(View.GONE);
            space2.setVisibility(View.GONE);
            space.setVisibility(View.VISIBLE);
            tv_start.setText("Start a Conversation.");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_message.getWindowToken(), 0);
        } else {
            cl_message.setVisibility(View.VISIBLE);
            space2.setVisibility(View.VISIBLE);
            space.setVisibility(View.GONE);
            tv_start.setText("Hide Message Box.");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_message, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
