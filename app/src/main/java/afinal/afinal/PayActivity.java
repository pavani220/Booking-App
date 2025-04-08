package afinal.afinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afinal.afinal.R;

public class PayActivity extends AppCompatActivity {

    private static final String TAG = "PayActivity";  // Log tag for debugging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);  // Ensure this layout exists

        // Find the button by its ID
        Button payButton = findViewById(R.id.button);

        // Set an OnClickListener to the button to trigger the PhonePe payment
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to open PhonePe directly with the Merchant UPI ID
                openPhonePePayment();
            }
        });
    }

    private void openPhonePePayment() {
        try {
            // Replace with the actual Merchant UPI ID (this is the ID the customer will use to make the payment)
            String merchantUpiId = "merchant@upi";  // Replace with your actual UPI ID

            // Create the intent to launch PhonePe
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("upi://pay?pa=" + merchantUpiId + "&pn=MerchantName&mc=0000&tid=" + System.currentTimeMillis() + "&txn=123456&amo=1&cu=INR&url=https://www.phonepe.com"));

            // Verify if there is an app that can handle this intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);  // Open PhonePe directly
            } else {
                Toast.makeText(this, "PhonePe is not installed on your device", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            // Log error and show a user-friendly error message
            Log.e(TAG, "Error opening PhonePe for payment", e);
            Toast.makeText(this, "Error opening PhonePe for payment", Toast.LENGTH_LONG).show();
        }
    }
}
