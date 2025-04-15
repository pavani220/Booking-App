package afinal.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afinal.afinal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingFormActivity extends AppCompatActivity {

    private EditText etName, etPhone, etDate, etAddress, etHectares;
    private RadioGroup rgService;
    private DatabaseReference bookingsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.afinal.afinal.R.layout.activity_booking_form);

        // Initialize UI components
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etDate = findViewById(R.id.et_date_of_booking);
        etAddress = findViewById(R.id.et_address);
        etHectares = findViewById(R.id.et_hectares);
        rgService = findViewById(R.id.rg_service);
        Button btnSubmitBooking = findViewById(R.id.btn_submit_booking);

        // Initialize Firebase Realtime Database reference
        bookingsRef = FirebaseDatabase.getInstance().getReference("bookings");

        // Retrieve the selected date from the Intent
        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("SELECTED_DATE");

        // If the date is not null, set it in the EditText
        if (selectedDate != null && !selectedDate.isEmpty()) {
            etDate.setText(selectedDate);  // Display the selected date
        }

        // Set listener on Submit button
        btnSubmitBooking.setOnClickListener(v -> {
            // Get the form data
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String hectares = etHectares.getText().toString().trim();

            // Get selected service
            int selectedServiceId = rgService.getCheckedRadioButtonId();
            RadioButton selectedService = findViewById(selectedServiceId);
            String service = selectedService != null ? selectedService.getText().toString() : "";

            // Validate all fields
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            } else if (phone.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            } else if (!phone.matches("[0-9]+") || phone.length() != 10) {
                Toast.makeText(this, "Please enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
            } else if (date.isEmpty()) {
                Toast.makeText(this, "Please enter the date of booking", Toast.LENGTH_SHORT).show();
            } else if (address.isEmpty()) {
                Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show();
            } else if (hectares.isEmpty()) {
                Toast.makeText(this, "Please enter the number of hectares", Toast.LENGTH_SHORT).show();
            } else {
                // Validate hectares input (ensure it's a valid number)
                try {
                    double hectaresValue = Double.parseDouble(hectares);
                    if (hectaresValue <= 0) {
                        Toast.makeText(this, "Hectares must be greater than zero", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (service.isEmpty()) {
                        Toast.makeText(this, "Please select a service", Toast.LENGTH_SHORT).show();
                    } else {
                        // If all fields are valid, save to Realtime Database
                        saveBookingDataToDatabase(name, phone, date, address, hectaresValue, service);
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please enter a valid number for hectares", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveBookingDataToDatabase(String name, String phone, String date, String address, double hectares, String service) {
        // Create a new Booking object
        Booking booking = new Booking(name, phone, date, address, hectares, service);

        // Log the Booking object to verify the data
        Log.d("BookingData", "Booking Object: " + booking.toString());

        // Generate a unique key for each booking and save it to the "bookings" node
        String bookingId = bookingsRef.push().getKey();
        if (bookingId != null) {
            bookingsRef.child(bookingId).setValue(booking)
                    .addOnSuccessListener(aVoid -> {
                        // Display success message
                        Toast.makeText(this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
                        finish();  // Close this activity and go back to the previous screen
                    })
                    .addOnFailureListener(e -> {
                        // Log the error and display error message
                        Log.e("FirebaseError", "Failed to confirm booking: " + e.getMessage());
                        Toast.makeText(this, "Failed to confirm booking", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Failed to generate booking ID", Toast.LENGTH_SHORT).show();
        }
    }

    // Define the Booking class
    public static class Booking {
        String name, phone, date, address, service;
        double hectares;

        // Default constructor for Firebase
        public Booking() {
        }

        public Booking(String name, String phone, String date, String address, double hectares, String service) {
            this.name = name;
            this.phone = phone;
            this.date = date;
            this.address = address;
            this.hectares = hectares;
            this.service = service;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getDate() {
            return date;
        }

        public String getAddress() {
            return address;
        }

        public double getHectares() {
            return hectares;
        }

        public String getService() {
            return service;
        }

        @Override
        public String toString() {
            return "Booking{" +
                    "name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", date='" + date + '\'' +
                    ", address='" + address + '\'' +
                    ", hectares=" + hectares +
                    ", service='" + service + '\'' +
                    '}';
        }
    }
}

