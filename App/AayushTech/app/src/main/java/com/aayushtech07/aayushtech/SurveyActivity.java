package com.aayushtech07.aayushtech;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SurveyActivity extends AppCompatActivity {

    private RadioGroup surveyRadioGroup;
    private EditText otherEditText;
    private Button submitButton;
    private CardView thankYouCard;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        initViews();
        setupListeners();
        
        prefs = getSharedPreferences("AayushTechPrefs", MODE_PRIVATE);
        
        // Check if survey already completed
        if (prefs.getBoolean("survey_completed", false)) {
            showThankYou();
        }
    }

    private void initViews() {
        surveyRadioGroup = findViewById(R.id.surveyRadioGroup);
        otherEditText = findViewById(R.id.otherEditText);
        submitButton = findViewById(R.id.submitButton);
        thankYouCard = findViewById(R.id.thankYouCard);
    }

    private void setupListeners() {
        surveyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_other) {
                otherEditText.setVisibility(View.VISIBLE);
            } else {
                otherEditText.setVisibility(View.GONE);
            }
        });

        submitButton.setOnClickListener(v -> submitSurvey());
    }

    private void submitSurvey() {
        int selectedId = surveyRadioGroup.getCheckedRadioButtonId();
        
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedOption = selectedRadioButton.getText().toString();
        
        // If "Other" is selected, get the text from EditText
        if (selectedId == R.id.radio_other) {
            String otherText = otherEditText.getText().toString().trim();
            if (!otherText.isEmpty()) {
                selectedOption = "Other: " + otherText;
            }
        }

        // Save survey response
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("survey_response", selectedOption);
        editor.putBoolean("survey_completed", true);
        editor.putLong("survey_date", System.currentTimeMillis());
        editor.apply();

        // Show thank you message
        showThankYou();
        
        Toast.makeText(this, getString(R.string.thank_you_survey), Toast.LENGTH_SHORT).show();
    }

    private void showThankYou() {
        surveyRadioGroup.setVisibility(View.GONE);
        otherEditText.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        thankYouCard.setVisibility(View.VISIBLE);
    }
}
