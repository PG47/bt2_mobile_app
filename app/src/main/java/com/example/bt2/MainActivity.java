package com.example.bt2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText dateEditText = findViewById(R.id.Date_textfield);

        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed for this example
            }

            @Override
            public void afterTextChanged(Editable editable) {
                formatDateString(editable);
            }
        });
    }

    public void showDatePicker(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, year, month, day) -> {
                    // Handle the selected date here
                    String formattedDate = String.format("%02d/%02d/%04d", day, month + 1, year);
                    EditText dateEditText = findViewById(R.id.Date_textfield);
                    dateEditText.setText(formattedDate);
                },
                // Set initial date to current date
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void formatDateString(Editable editable) {
        String input = editable.toString();

        if (input.length() == 3 || input.length() == 6) {
            editable.delete(input.length() - 1, input.length());
            return;
        }

        if (input.length() == 2 || input.length() == 5) {
            // Add a "/" after the second and fifth character
            editable.append("/");
        }
    }



}