package com.example.demo.entity;


import lombok.Data;

public class SimpleSource {
    private String name;
    private String description;

    public SimpleSource() {
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
}