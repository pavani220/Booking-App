package com.example.afinal.ui.slideshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.ui.slideshow.ChatMessage;

import java.util.List;

public class ChatbotAdapter extends RecyclerView.Adapter<ChatbotAdapter.ChatMessageViewHolder> {

    private List<com.example.afinal.ui.slideshow.ChatMessage> chatMessages;


    public ChatbotAdapter(List<ChatMessage> chatMessages) {
    }

    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == com.example.afinal.ui.slideshow.ChatMessage.TYPE_USER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_message, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bot_message, parent, false);
        }
        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {
        com.example.afinal.ui.slideshow.ChatMessage chatMessage = chatMessages.get(position);
        holder.textView.setText(chatMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getMessageType();
    }

    public static class ChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ChatMessageViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textMessage);
        }
    }
}
