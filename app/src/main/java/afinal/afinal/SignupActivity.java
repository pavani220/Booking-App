package afinal.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.afinal.afinal.R;

public class SignupActivity extends AppCompatActivity {
    EditText username, email, password;
    Button signupBtn;
    TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupBtn);
        toLogin = findViewById(R.id.toLogin);

        // Redirect to LoginActivity if the user already has an account
        toLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish(); // Close SignupActivity
        });

        // Handle signup button click
        signupBtn.setOnClickListener(v -> {
            // Get user input values
            String enteredUsername = username.getText().toString().trim();
            String enteredEmail = email.getText().toString().trim();
            String enteredPassword = password.getText().toString().trim();

            // Check if fields are empty
            if (enteredUsername.isEmpty()) {
                username.setError("Username is required");
                return;
            }

            if (enteredEmail.isEmpty()) {
                email.setError("Email is required");
                return;
            }

            if (enteredPassword.isEmpty()) {
                password.setError("Password is required");
                return;
            }

            // Save the user data in SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", enteredEmail);
            editor.putString("password", enteredPassword);
            editor.apply();

            // After successful signup, show a success message
            Toast.makeText(SignupActivity.this, "Sign up successful! Please log in.", Toast.LENGTH_SHORT).show();

            // Redirect to LoginActivity
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close SignupActivity to prevent going back
        });
    }
}