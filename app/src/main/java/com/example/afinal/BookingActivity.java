package com.example.afinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        // Set listener for date selection on the CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Display the selected date as a Toast (you can use this data as needed)
            String selectedDate = "Selected Date: " + dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(BookingActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
        });

        // Set listener for Confirm Booking button
        confirmButton.setOnClickListener(v -> {
            // You can implement the booking confirmation logic here
            Toast.makeText(BookingActivity.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
        });
    }
}
