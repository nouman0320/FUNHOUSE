package com.programrabbit.funhouse.Holder;

import android.view.View;
import android.widget.TextView;

import com.programrabbit.funhouse.Model.ChatMessage;
import com.programrabbit.funhouse.R;

import java.text.SimpleDateFormat;

import androidx.recyclerview.widget.RecyclerView;

public class SentMessageHolder extends RecyclerView.ViewHolder{
    TextView messageText, timeText, nameText;
    //ImageView profileImage;

    public SentMessageHolder(View itemView) {
        super(itemView);
        messageText = itemView.findViewById(R.id.tv_message);
        timeText = itemView.findViewById(R.id.tv_time);
        nameText = itemView.findViewById(R.id.tv_username);
        //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
    }

    void bind(ChatMessage message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.

        SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm a");
        String time = localDateFormat.format(message.getCreatedAt());
        timeText.setText(time);
        nameText.setText(message.getUser().getUsername());

        // Insert the profile image from the URL into the ImageView.
        //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
    }

}

