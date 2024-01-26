package com.example.bt2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class resultform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultform);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Retrieve data from the Intent
        String username = intent.getStringExtra("USERNAME");
        String password = intent.getStringExtra("PASSWORD");
        String dateOfBirth = intent.getStringExtra("DATE_OF_BIRTH");
        String gender = intent.getStringExtra("GENDER");
        String hobbies = intent.getStringExtra("HOBBIES");

        String maskedPassword = maskPassword(password);

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Username: " + username + "\n\n" +
                "Password: " + maskedPassword + "\n\n" +
                "Birthdate: " + dateOfBirth + "\n\n" +
                "Gender: " + gender + "\n\n" +
                "Hobbies: " + hobbies);
    }

    private String maskPassword(String password) {
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            maskedPassword.append("*");
        }
        return maskedPassword.toString();
    }

    public void Exit() {
        finishAffinity();
        System.exit(0);
    }
}
