package com.aayushtech07.aayushtech;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aayushtech07.aayushtech.adapters.AppShortcutAdapter;
import com.aayushtech07.aayushtech.adapters.ToolsAdapter;
import com.aayushtech07.aayushtech.models.AppShortcut;
import com.aayushtech07.aayushtech.models.Tool;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView shortcutsRecyclerView;
    private RecyclerView toolsRecyclerView;
    private AppShortcutAdapter shortcutAdapter;
    private ToolsAdapter toolsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerViews();
        setupClickListeners();
    }

    private void initViews() {
        shortcutsRecyclerView = findViewById(R.id.shortcutsRecyclerView);
        toolsRecyclerView = findViewById(R.id.toolsRecyclerView);
    }

    private void setupRecyclerViews() {
        // Setup shortcuts RecyclerView
        GridLayoutManager shortcutsLayoutManager = new GridLayoutManager(this, 4);
        shortcutsRecyclerView.setLayoutManager(shortcutsLayoutManager);
        
        List<AppShortcut> shortcuts = createAppShortcuts();
        shortcutAdapter = new AppShortcutAdapter(shortcuts, this::openApp);
        shortcutsRecyclerView.setAdapter(shortcutAdapter);

        // Setup tools RecyclerView
        GridLayoutManager toolsLayoutManager = new GridLayoutManager(this, 3);
        toolsRecyclerView.setLayoutManager(toolsLayoutManager);
        
        List<Tool> tools = createTools();
        toolsAdapter = new ToolsAdapter(tools, this::openTool);
        toolsRecyclerView.setAdapter(toolsAdapter);
    }

    private void setupClickListeners() {
        findViewById(R.id.surveyCard).setOnClickListener(v -> {
            Intent intent = new Intent(this, SurveyActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.aboutCard).setOnClickListener(v -> {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });
    }

    private List<AppShortcut> createAppShortcuts() {
        List<AppShortcut> shortcuts = new ArrayList<>();
        
        shortcuts.add(new AppShortcut("Facebook", "com.facebook.katana", R.drawable.ic_facebook));
        shortcuts.add(new AppShortcut("YouTube", "com.google.android.youtube", R.drawable.ic_youtube));
        shortcuts.add(new AppShortcut("Google", "com.google.android.googlequicksearchbox", R.drawable.ic_google));
        shortcuts.add(new AppShortcut("WhatsApp", "com.whatsapp", R.drawable.ic_whatsapp));
        shortcuts.add(new AppShortcut("Instagram", "com.instagram.android", R.drawable.ic_instagram));
        shortcuts.add(new AppShortcut("Twitter", "com.twitter.android", R.drawable.ic_twitter));
        shortcuts.add(new AppShortcut("Gmail", "com.google.android.gm", R.drawable.ic_gmail));
        shortcuts.add(new AppShortcut("Chrome", "com.android.chrome", R.drawable.ic_chrome));
        shortcuts.add(new AppShortcut("Play Store", "com.android.vending", R.drawable.ic_play_store));
        shortcuts.add(new AppShortcut("Settings", "com.android.settings", R.drawable.ic_settings));
        shortcuts.add(new AppShortcut("Maps", "com.google.android.apps.maps", R.drawable.ic_maps));
        shortcuts.add(new AppShortcut("Photos", "com.google.android.apps.photos", R.drawable.ic_photos));
        
        return shortcuts;
    }

    private List<Tool> createTools() {
        List<Tool> tools = new ArrayList<>();
        
        tools.add(new Tool("Calculator", "Perform calculations", R.drawable.ic_calculator, Tool.TYPE_CALCULATOR));
        tools.add(new Tool("Flashlight", "Turn on/off flashlight", R.drawable.ic_flashlight, Tool.TYPE_FLASHLIGHT));
        tools.add(new Tool("QR Scanner", "Scan QR codes", R.drawable.ic_qr_code, Tool.TYPE_QR_SCANNER));
        tools.add(new Tool("Unit Converter", "Convert units", R.drawable.ic_converter, Tool.TYPE_UNIT_CONVERTER));
        tools.add(new Tool("Notes", "Quick notes", R.drawable.ic_notes, Tool.TYPE_NOTES));
        tools.add(new Tool("Color Picker", "Pick colors", R.drawable.ic_color_picker, Tool.TYPE_COLOR_PICKER));
        tools.add(new Tool("Ruler", "Measure objects", R.drawable.ic_ruler, Tool.TYPE_RULER));
        tools.add(new Tool("Compass", "Find directions", R.drawable.ic_compass, Tool.TYPE_COMPASS));
        tools.add(new Tool("Password Gen", "Generate passwords", R.drawable.ic_password, Tool.TYPE_PASSWORD_GENERATOR));
        
        return tools;
    }

    private void openApp(AppShortcut shortcut) {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(shortcut.getPackageName());
            if (intent != null) {
                startActivity(intent);
            } else {
                // Try to open in Play Store if app not installed
                try {
                    Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, 
                        Uri.parse("market://details?id=" + shortcut.getPackageName()));
                    startActivity(playStoreIntent);
                } catch (Exception e) {
                    Toast.makeText(this, getString(R.string.app_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error opening app", Toast.LENGTH_SHORT).show();
        }
    }

    private void openTool(Tool tool) {
        Intent intent = new Intent(this, ToolsActivity.class);
        intent.putExtra("tool_type", tool.getType());
        intent.putExtra("tool_name", tool.getName());
        startActivity(intent);
    }
}
