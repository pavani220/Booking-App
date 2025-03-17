package com.example.afinal.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afinal.R;
import com.example.afinal.databinding.FragmentSlideshowBinding;
import com.example.afinal.ui.slideshow.ChatMessage;
import com.example.afinal.ui.slideshow.ChatbotAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private RecyclerView recyclerView;
    private ChatbotAdapter adapter;
    private List<ChatMessage> chatMessages;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize RecyclerView for chatbot
        recyclerView = root.findViewById(R.id.recyclerViewMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize chat message list and adapter
        chatMessages = new ArrayList<>();
        adapter = new ChatbotAdapter(chatMessages);
        recyclerView.setAdapter(adapter);

        // Input field and send button
        EditText editTextMessage = root.findViewById(R.id.editTextMessage);
        Button btnSendMessage = root.findViewById(R.id.btnSendMessage);

        // On send button click, send message and get response
        btnSendMessage.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString();
            if (!message.isEmpty()) {
                sendMessage(message);
                editTextMessage.setText("");  // Clear input box
            }
        });

        return root;
    }

    // Method to send a user message and generate bot response
    private void sendMessage(String message) {
        // Add user message to the chat
        chatMessages.add(new ChatMessage(message, ChatMessage.TYPE_USER));
        adapter.notifyDataSetChanged();

        // Generate bot's response
        String botResponse = generateBotResponse(message);
        chatMessages.add(new ChatMessage(botResponse, ChatMessage.TYPE_BOT));
        adapter.notifyDataSetChanged();
    }

    // Simple logic to generate bot response
    private String generateBotResponse(String userMessage) {
        userMessage = userMessage.toLowerCase();
        if (userMessage.contains("hello")) {
            return "Hello, Welcome to Vurimi AI Global Services, how can I help you today?";
        } else if (userMessage.contains("help")) {
            return "How can I assist you further? Please specify your request.";
        } else {
            return "I am sorry, I don't understand your query.";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
