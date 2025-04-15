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

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginBtn;
    TextView toSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        toSignup = findViewById(R.id.toSignup);

        // Redirect to SignupActivity if user doesn't have an account
        toSignup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            finish();
        });

        loginBtn.setOnClickListener(v -> {
            String enteredEmail = email.getText().toString().trim();
            String enteredPassword = password.getText().toString().trim();

            // Validate fields
            if (enteredEmail.isEmpty()) {
                email.setError("Email is required");
                return;
            }

            if (enteredPassword.isEmpty()) {
                password.setError("Password is required");
                return;
            }

            // Check if the email and password match the stored data (this example uses SharedPreferences)
            SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
            String storedEmail = sharedPreferences.getString("email", "");
            String storedPassword = sharedPreferences.getString("password", "");

            // Check if the entered credentials match the stored credentials
            if (enteredEmail.equals(storedEmail) && enteredPassword.equals(storedPassword)) {
                // Mark the user as logged in
                SessionManager sessionManager = new SessionManager(LoginActivity.this);
                sessionManager.setLogin(true); // Set login status to true

                // Navigate to MainActivity (Home Page)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close LoginActivity
            } else {
                // Show error message if credentials are incorrect
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
