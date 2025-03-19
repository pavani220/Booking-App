package com.example.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);  // Ensure this is your layout XML file

        // Initialize CalendarView and Button
        calendarView = findViewById(R.id.calendarView);
        confirmButton = findViewById(R.id.btn_confirm_booking);

        // Set today's date as the minimum date to disable past dates
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendarView.setMinDate(currentDate);  // Disable past dates

        // Set listener for date selection on the CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Display the selected date as a Toast (you can use this data as needed)
            String selectedDate = "Selected Date: " + dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(BookingActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
        });

        // Set listener for Confirm Booking button
        confirmButton.setOnClickListener(v -> {
            // Implement booking confirmation logic here
            Toast.makeText(BookingActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();

            // Open the Google Form after confirming booking
            openGoogleForm();

            // Start the PaymentActivity when booking is confirmed
            Intent intent = new Intent(BookingActivity.this, PayActivity.class);
            startActivity(intent);
        });
    }

    // Method to open the Google Form using its URL
    private void openGoogleForm() {
        try {
            // Replace this with your actual Google Form URL
            String googleFormUrl = "https://forms.gle/tqT22mxWpazFF7t9A";  // Use the actual URL of your Google Form

            // Create an Intent to open the URL in a browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleFormUrl));
            startActivity(intent);  // Open the Google Form in the browser or app
        } catch (Exception e) {
            // Handle any errors (e.g., URL malformed)
            Toast.makeText(this, "Failed to open Google Form", Toast.LENGTH_SHORT).show();
        }
    }
}


//https://forms.gle/RPxrnXzzHvTTf9hq5