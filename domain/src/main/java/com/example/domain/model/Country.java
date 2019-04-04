package com.example.domain.model;

public class Country {
    private final long id;
    private final String name;

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
