package com.example.ayanheili_id;

public class Quest {
    private int id;
    private String title;
    private String description;
    private int xp;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getXp() { return xp; }

    @Override
    public String toString() {
        return title;
    }
}
