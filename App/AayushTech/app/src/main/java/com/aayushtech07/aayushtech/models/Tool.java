package com.aayushtech07.aayushtech.models;

public class Tool {
    public static final int TYPE_CALCULATOR = 1;
    public static final int TYPE_FLASHLIGHT = 2;
    public static final int TYPE_QR_SCANNER = 3;
    public static final int TYPE_UNIT_CONVERTER = 4;
    public static final int TYPE_NOTES = 5;
    public static final int TYPE_COLOR_PICKER = 6;
    public static final int TYPE_RULER = 7;
    public static final int TYPE_COMPASS = 8;
    public static final int TYPE_PASSWORD_GENERATOR = 9;
    public static final int TYPE_WIFI_ANALYZER = 10;

    private String name;
    private String description;
    private int iconResId;
    private int type;

    public Tool(String name, String description, int iconResId, int type) {
        this.name = name;
        this.description = description;
        this.iconResId = iconResId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
