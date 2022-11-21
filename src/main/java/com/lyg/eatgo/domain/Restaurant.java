package com.lyg.eatgo.domain;

public class Restaurant {

    private final String address;
    private final String name;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return  "Bob zip";
    }

    public String getInformation() {
        return name + " in " + address;
    }

    public String getAddress() {
        return address;
    }
}
