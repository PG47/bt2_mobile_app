package com.example.bt2;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    EditText Username_textfield;
    EditText Password_textfield;
    EditText Retype_textfield;
    EditText dateEditText;
    RadioButton Male_button;
    RadioButton Female_button;
    CheckBox tennisCheckBox;
    CheckBox futbalCheckBox;
    CheckBox othersCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username_textfield = findViewById(R.id.Username_textfield);
        Password_textfield = findViewById(R.id.Password_textfield);
        Retype_textfield = findViewById(R.id.Retype_textfield);
        dateEditText = findViewById(R.id.Date_textfield);
        Male_button = findViewById(R.id.Male_button);
        Female_button = findViewById(R.id.Female_button);
        tennisCheckBox = findViewById(R.id.Tennis_cb);
        futbalCheckBox = findViewById(R.id.Futbal_cb);
        othersCheckBox = findViewById(R.id.Others_cb);

        dateEditText.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    dateEditText.setText(current);
                    dateEditText.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // If a checkbox is checked, uncheck the others
                if (isChecked) {
                    if (buttonView == tennisCheckBox) {
                        futbalCheckBox.setChecked(false);
                        othersCheckBox.setChecked(false);
                    } else if (buttonView == futbalCheckBox) {
                        tennisCheckBox.setChecked(false);
                        othersCheckBox.setChecked(false);
                    } else if (buttonView == othersCheckBox) {
                        tennisCheckBox.setChecked(false);
                        futbalCheckBox.setChecked(false);
                    }
                }
            }
        };

        tennisCheckBox.setOnCheckedChangeListener(checkBoxListener);
        futbalCheckBox.setOnCheckedChangeListener(checkBoxListener);
        othersCheckBox.setOnCheckedChangeListener(checkBoxListener);


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

    public void Reset(View view){
        Username_textfield.setText("");
        Password_textfield.setText("");
        Retype_textfield.setText("");
        dateEditText.setText("");
        Male_button.setChecked(false);
        Female_button.setChecked(false);
        tennisCheckBox.setChecked(false);
        futbalCheckBox.setChecked(false);
        othersCheckBox.setChecked(false);
    }

    private void formatDateString(Editable editable) {
        String input = editable.toString();
        if (input.length() == 2 || input.length() == 5) {
            // Add a "/" after the second and fifth character
            editable.append("/");
            return;
        }
    }



}