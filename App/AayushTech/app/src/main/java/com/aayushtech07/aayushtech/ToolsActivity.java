package com.aayushtech07.aayushtech;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.aayushtech07.aayushtech.models.Tool;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.security.SecureRandom;
import java.util.Random;

public class ToolsActivity extends AppCompatActivity {

    private int toolType;
    private String toolName;
    private LinearLayout contentContainer;
    private boolean flashlightOn = false;
    private Camera camera;
    private CameraManager cameraManager;
    private String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        toolType = getIntent().getIntExtra("tool_type", 0);
        toolName = getIntent().getStringExtra("tool_name");

        TextView titleTextView = findViewById(R.id.toolTitleTextView);
        titleTextView.setText(toolName);

        contentContainer = findViewById(R.id.contentContainer);

        setupToolContent();
    }

    private void setupToolContent() {
        switch (toolType) {
            case Tool.TYPE_CALCULATOR:
                setupCalculator();
                break;
            case Tool.TYPE_FLASHLIGHT:
                setupFlashlight();
                break;
            case Tool.TYPE_QR_SCANNER:
                setupQRScanner();
                break;
            case Tool.TYPE_UNIT_CONVERTER:
                setupUnitConverter();
                break;
            case Tool.TYPE_NOTES:
                setupNotes();
                break;
            case Tool.TYPE_COLOR_PICKER:
                setupColorPicker();
                break;
            case Tool.TYPE_PASSWORD_GENERATOR:
                setupPasswordGenerator();
                break;
            default:
                setupComingSoon();
                break;
        }
    }

    private void setupCalculator() {
        LinearLayout calculatorLayout = new LinearLayout(this);
        calculatorLayout.setOrientation(LinearLayout.VERTICAL);
        calculatorLayout.setPadding(16, 16, 16, 16);

        EditText displayEditText = new EditText(this);
        displayEditText.setTextSize(24);
        displayEditText.setHint("0");
        displayEditText.setEnabled(false);

        calculatorLayout.addView(displayEditText);

        TextView infoText = new TextView(this);
        infoText.setText("Basic Calculator - Feature coming in next update!");
        infoText.setPadding(16, 16, 16, 16);
        calculatorLayout.addView(infoText);

        contentContainer.addView(calculatorLayout);
    }

    private void setupFlashlight() {
        LinearLayout flashlightLayout = new LinearLayout(this);
        flashlightLayout.setOrientation(LinearLayout.VERTICAL);
        flashlightLayout.setPadding(16, 16, 16, 16);
        flashlightLayout.setGravity(android.view.Gravity.CENTER);

        Button flashlightButton = new Button(this);
        flashlightButton.setText(flashlightOn ? "Turn OFF Flashlight" : "Turn ON Flashlight");
        flashlightButton.setTextSize(18);
        flashlightButton.setPadding(32, 32, 32, 32);

        flashlightButton.setOnClickListener(v -> toggleFlashlight(flashlightButton));

        flashlightLayout.addView(flashlightButton);

        TextView instructionText = new TextView(this);
        instructionText.setText("Tap the button to toggle flashlight on/off");
        instructionText.setPadding(16, 16, 16, 16);
        instructionText.setGravity(android.view.Gravity.CENTER);
        flashlightLayout.addView(instructionText);

        contentContainer.addView(flashlightLayout);
    }

    private void setupQRScanner() {
        LinearLayout qrLayout = new LinearLayout(this);
        qrLayout.setOrientation(LinearLayout.VERTICAL);
        qrLayout.setPadding(16, 16, 16, 16);
        qrLayout.setGravity(android.view.Gravity.CENTER);

        Button scanButton = new Button(this);
        scanButton.setText("Start QR Code Scan");
        scanButton.setTextSize(18);
        scanButton.setPadding(32, 32, 32, 32);

        scanButton.setOnClickListener(v -> startQRScan());

        qrLayout.addView(scanButton);

        TextView instructionText = new TextView(this);
        instructionText.setText("Scan QR codes and barcodes easily");
        instructionText.setPadding(16, 16, 16, 16);
        instructionText.setGravity(android.view.Gravity.CENTER);
        qrLayout.addView(instructionText);

        contentContainer.addView(qrLayout);
    }

    private void setupUnitConverter() {
        LinearLayout converterLayout = new LinearLayout(this);
        converterLayout.setOrientation(LinearLayout.VERTICAL);
        converterLayout.setPadding(16, 16, 16, 16);

        TextView titleText = new TextView(this);
        titleText.setText("Length Converter (Demo)");
        titleText.setTextSize(18);
        titleText.setPadding(0, 0, 0, 16);
        converterLayout.addView(titleText);

        EditText inputEditText = new EditText(this);
        inputEditText.setHint("Enter meters");
        converterLayout.addView(inputEditText);

        Button convertButton = new Button(this);
        convertButton.setText("Convert to Feet");
        convertButton.setOnClickListener(v -> {
            String input = inputEditText.getText().toString();
            if (!input.isEmpty()) {
                try {
                    double meters = Double.parseDouble(input);
                    double feet = meters * 3.28084;
                    Toast.makeText(this, String.format("%.2f meters = %.2f feet", meters, feet), Toast.LENGTH_LONG).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        converterLayout.addView(convertButton);

        contentContainer.addView(converterLayout);
    }

    private void setupNotes() {
        LinearLayout notesLayout = new LinearLayout(this);
        notesLayout.setOrientation(LinearLayout.VERTICAL);
        notesLayout.setPadding(16, 16, 16, 16);

        TextView titleText = new TextView(this);
        titleText.setText("Quick Notes");
        titleText.setTextSize(18);
        titleText.setPadding(0, 0, 0, 16);
        notesLayout.addView(titleText);

        EditText notesEditText = new EditText(this);
        notesEditText.setHint("Type your notes here...");
        notesEditText.setMinLines(5);
        notesEditText.setGravity(android.view.Gravity.TOP);
        notesLayout.addView(notesEditText);

        Button saveButton = new Button(this);
        saveButton.setText("Save Note");
        saveButton.setOnClickListener(v -> {
            String note = notesEditText.getText().toString();
            if (!note.isEmpty()) {
                // In a real app, you'd save to database or file
                Toast.makeText(this, "Note saved! (Demo mode)", Toast.LENGTH_SHORT).show();
            }
        });
        notesLayout.addView(saveButton);

        contentContainer.addView(notesLayout);
    }

    private void setupColorPicker() {
        LinearLayout colorLayout = new LinearLayout(this);
        colorLayout.setOrientation(LinearLayout.VERTICAL);
        colorLayout.setPadding(16, 16, 16, 16);

        TextView titleText = new TextView(this);
        titleText.setText("Random Color Generator");
        titleText.setTextSize(18);
        titleText.setPadding(0, 0, 0, 16);
        colorLayout.addView(titleText);

        View colorView = new View(this);
        colorView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        colorView.setBackgroundColor(getRandomColor());
        colorLayout.addView(colorView);

        TextView colorCodeText = new TextView(this);
        colorCodeText.setPadding(0, 16, 0, 16);
        colorLayout.addView(colorCodeText);

        Button generateButton = new Button(this);
        generateButton.setText("Generate New Color");
        generateButton.setOnClickListener(v -> {
            int color = getRandomColor();
            colorView.setBackgroundColor(color);
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            colorCodeText.setText("Color: " + hexColor);
        });
        colorLayout.addView(generateButton);

        // Initialize with first color
        int initialColor = getRandomColor();
        colorView.setBackgroundColor(initialColor);
        String hexColor = String.format("#%06X", (0xFFFFFF & initialColor));
        colorCodeText.setText("Color: " + hexColor);

        contentContainer.addView(colorLayout);
    }

    private void setupPasswordGenerator() {
        LinearLayout passwordLayout = new LinearLayout(this);
        passwordLayout.setOrientation(LinearLayout.VERTICAL);
        passwordLayout.setPadding(16, 16, 16, 16);

        TextView titleText = new TextView(this);
        titleText.setText("Password Generator");
        titleText.setTextSize(18);
        titleText.setPadding(0, 0, 0, 16);
        passwordLayout.addView(titleText);

        TextView passwordText = new TextView(this);
        passwordText.setTextSize(16);
        passwordText.setPadding(16, 16, 16, 16);
        passwordText.setBackgroundColor(0xFFF5F5F5);
        passwordLayout.addView(passwordText);

        Button generateButton = new Button(this);
        generateButton.setText("Generate Password");
        generateButton.setOnClickListener(v -> {
            String password = generateRandomPassword(12);
            passwordText.setText(password);
        });
        passwordLayout.addView(generateButton);

        Button copyButton = new Button(this);
        copyButton.setText("Copy to Clipboard");
        copyButton.setOnClickListener(v -> {
            String password = passwordText.getText().toString();
            if (!password.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Password", password);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Password copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });
        passwordLayout.addView(copyButton);

        contentContainer.addView(passwordLayout);
    }

    private void setupComingSoon() {
        TextView comingSoonText = new TextView(this);
        comingSoonText.setText("This feature is coming soon!\n\nStay tuned for updates.");
        comingSoonText.setTextSize(18);
        comingSoonText.setPadding(16, 16, 16, 16);
        comingSoonText.setGravity(android.view.Gravity.CENTER);
        contentContainer.addView(comingSoonText);
    }

    private void toggleFlashlight(Button button) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                cameraId = cameraManager.getCameraIdList()[0];
                
                if (!flashlightOn) {
                    cameraManager.setTorchMode(cameraId, true);
                    flashlightOn = true;
                    button.setText("Turn OFF Flashlight");
                } else {
                    cameraManager.setTorchMode(cameraId, false);
                    flashlightOn = false;
                    button.setText("Turn ON Flashlight");
                }
            } catch (Exception e) {
                Toast.makeText(this, "Flashlight not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Flashlight requires Android 6.0+", Toast.LENGTH_SHORT).show();
        }
    }

    private void startQRScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan QR Code or Barcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Scan cancelled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // You can process the scanned content here
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private int getRandomColor() {
        Random random = new Random();
        return 0xFF000000 | random.nextInt(0x00FFFFFF);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return password.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (flashlightOn && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                cameraManager.setTorchMode(cameraId, false);
            } catch (Exception e) {
                // Handle exception
            }
        }
    }
}
