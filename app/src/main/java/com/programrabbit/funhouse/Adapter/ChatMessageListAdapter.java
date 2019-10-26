package com.programrabbit.funhouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.programrabbit.funhouse.Holder.ReceivedMessageHolder;
import com.programrabbit.funhouse.Holder.SentMessageHolder;
import com.programrabbit.funhouse.Model.ChatMessage;
import com.programrabbit.funhouse.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatMessageListAdapter extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private Context mContext;
    private ArrayList<ChatMessage> mMessageList;

    public ChatMessageListAdapter(Context context, ArrayList<ChatMessage> messageList) {
        mContext = context;
        mMessageList = messageList;
    }


    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        ChatMessage message = (ChatMessage) mMessageList.get(position);

        // for testing
        String currentUsername = "Steve";
        //

        if (message.getUser().getUsername().equals(currentUsername)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }


    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }


    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.tv_message);
            timeText = itemView.findViewById(R.id.tv_time);
            nameText = itemView.findViewById(R.id.tv_username);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessage());

            SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm a");
            String time = localDateFormat.format(message.getCreatedAt());
            // Format the stored timestamp into a readable String using method.
            timeText.setText(time);

            nameText.setText(message.getUser().getUsername());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        //ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.tv_message_r);
            timeText = itemView.findViewById(R.id.tv_time_r);
            nameText = itemView.findViewById(R.id.tv_username_r);
            //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(ChatMessage message) {
            messageText.setText(message.getMessage());

            SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm a");
            String time = localDateFormat.format(message.getCreatedAt());

            // Format the stored timestamp into a readable String using method.
            timeText.setText(time);

            nameText.setText(message.getUser().getUsername());

            // Insert the profile image from the URL into the ImageView.
            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }
}
