package com.example.afinal.ui.home;

import android.content.Intent;
import android.net.Uri;  // Import for Uri
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.afinal.BookingActivity;  // Import the BookingActivity
import com.example.afinal.R;
import com.example.afinal.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 viewPager2;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the ViewPager2
        viewPager2 = binding.getRoot().findViewById(R.id.viewPager2);

        // Get the image resources from the ViewModel
        homeViewModel.getImages().observe(getViewLifecycleOwner(), images -> {
            HomeImageAdapter adapter = new HomeImageAdapter(images);
            viewPager2.setAdapter(adapter);
        });

        // Automatically slide images
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0; // Reset to first image if last image is reached
                }
                viewPager2.setCurrentItem(currentPage++, true); // Slide the images
                handler.postDelayed(this, 3000); // Slide every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000); // Start after 3 seconds

        // Handle the "Book Now" button click for BookingActivity
        Button bookNowButton = root.findViewById(R.id.btn_book_now);
        bookNowButton.setOnClickListener(view -> {
            // Navigate to the BookingActivity when the button is clicked
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            startActivity(intent);
        });

        // Handle the "Book Now" button click for Google Form (btn_book_now2)
        Button bookNowButton2 = root.findViewById(R.id.btn_book_now2);
        bookNowButton2.setOnClickListener(view -> {
            // Open the Google Form when the button is clicked
            openGoogleForm();
        });

        return root;
    }

    // Method to open the Google Form
    private void openGoogleForm() {
        try {
            // Replace with your actual Google Form URL
            String googleFormUrl = "https://forms.gle/qfSMnQeUxEujUgGm8";  // Use the actual URL of your Google Form

            // Create an Intent to open the Google Form in a browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleFormUrl));
            startActivity(intent);  // Open the Google Form in the browser or app
        } catch (Exception e) {
            // Handle any errors (e.g., URL malformed)
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (handler != null) {
            handler.removeCallbacks(runnable); // Stop the sliding when the fragment is destroyed
        }
    }
}
