package com.aayushtech07.aayushtech;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupViews();
    }

    private void setupViews() {
        TextView appNameText = findViewById(R.id.appNameTextView);
        TextView versionText = findViewById(R.id.versionTextView);
        TextView developerText = findViewById(R.id.developerTextView);
        TextView companyText = findViewById(R.id.companyTextView);
        TextView descriptionText = findViewById(R.id.descriptionTextView);
        
        Button shareButton = findViewById(R.id.shareButton);
        Button rateButton = findViewById(R.id.rateButton);

        appNameText.setText(getString(R.string.app_name));
        versionText.setText(getString(R.string.version_info));
        developerText.setText(getString(R.string.developer_info));
        companyText.setText(getString(R.string.company_info));
        descriptionText.setText(getString(R.string.about_description));

        shareButton.setOnClickListener(v -> shareApp());
        rateButton.setOnClickListener(v -> rateApp());
    }

    private void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out Aayush Tech!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, 
            "Hey! Check out this amazing app called Aayush Tech - it has tons of useful daily life tools and shortcuts. " +
            "Download it from the Play Store!");
        
        startActivity(Intent.createChooser(shareIntent, "Share Aayush Tech"));
    }

    private void rateApp() {
        try {
            Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + getPackageName()));
            startActivity(rateIntent);
        } catch (Exception e) {
            Intent rateIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
            startActivity(rateIntent);
        }
    }
}
