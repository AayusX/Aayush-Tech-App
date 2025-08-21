package com.aayushtech07.aayushtech.models;

public class AppShortcut {
    private String name;
    private String packageName;
    private int iconResId;

    public AppShortcut(String name, String packageName, int iconResId) {
        this.name = name;
        this.packageName = packageName;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
