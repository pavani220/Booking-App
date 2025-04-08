package afinal.afinal.ui.slideshow;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afinal.afinal.R;

import java.util.Arrays;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private EditText nameEditText, emailEditText, messageEditText;
    private Button submitButton;
    private RecyclerView timelineRecyclerView;
    private TimelineAdapter timelineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        // Find the form fields and submit button
        nameEditText = root.findViewById(R.id.nameEditText);
        emailEditText = root.findViewById(R.id.emailEditText);
        messageEditText = root.findViewById(R.id.messageEditText);
        submitButton = root.findViewById(R.id.submitButton);

        // Find the RecyclerView for the timeline
        timelineRecyclerView = root.findViewById(R.id.timelineRecyclerView);
        timelineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // List of timeline locations
        List<String> timelineItems = Arrays.asList("Nellore", "Hyderabad", "Warangal", "Vijayawada", "Siricilla","NagarKurnool");

        // Set the adapter for RecyclerView
        timelineAdapter = new TimelineAdapter(timelineItems);
        timelineRecyclerView.setAdapter(timelineAdapter);

        // Set up the submit button click listener
        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String message = messageEditText.getText().toString().trim();

            // Check if the fields are empty
            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Here you can add code to send the form data to your backend (e.g., Firebase or an API).
                Toast.makeText(getContext(), "Your message has been sent!", Toast.LENGTH_SHORT).show();
                clearFormFields();
            }
        });

        return root;
    }

    // Clear form fields after submission
    private void clearFormFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        messageEditText.setText("");
    }
}

