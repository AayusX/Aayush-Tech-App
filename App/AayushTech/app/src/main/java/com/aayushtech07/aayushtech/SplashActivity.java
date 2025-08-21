package com.aayushtech07.aayushtech;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.splashLogoImageView);
        TextView appNameTextView = findViewById(R.id.splashAppNameTextView);
        TextView taglineTextView = findViewById(R.id.splashTaglineTextView);

        // Animate logo
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1500);
        scaleAnimation.setFillAfter(true);
        logoImageView.startAnimation(scaleAnimation);

        // Fade in text after logo animation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            appNameTextView.animate().alpha(1.0f).setDuration(800).start();
            taglineTextView.animate().alpha(1.0f).setDuration(800).setStartDelay(200).start();
        }, 800);

        // Navigate to MainActivity after splash duration
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close splash activity
        }, SPLASH_DURATION);
    }
}
