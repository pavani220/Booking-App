package afinal.afinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afinal.afinal.R;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize CalendarView and Button
        calendarView = findViewById(R.id.calendarView);
        Button confirmButton = findViewById(R.id.btn_confirm_booking);

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
            // Get the selected date from CalendarView
            long selectedDateInMillis = calendarView.getDate();
            @SuppressLint("SimpleDateFormat") String selectedDateString = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(selectedDateInMillis));

            // Create an Intent to pass the selected date
            Intent intent = new Intent(BookingActivity.this, BookingFormActivity.class);
            intent.putExtra("SELECTED_DATE", selectedDateString);
            startActivity(intent);
        });
    }
}
