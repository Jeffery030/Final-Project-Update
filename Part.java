package com.example;

import java.time.LocalDate;

public class Part {
    private String name;
    private LocalDate lastReplaced;
    private int replacementInterval;
    private String description;

    public Part(String name, LocalDate lastReplaced, int replacementInterval, String description) {
        this.name = name;
        this.lastReplaced = lastReplaced;
        this.replacementInterval = replacementInterval;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDue() {
        return LocalDate.now().isAfter(lastReplaced.plusDays(replacementInterval));
    }

    @Override
    public String toString() {
        return name;
    }
}
