package com.lyg.eatgo.domain;

public class Restaurant {

    private final Long id;
    private final String address;
    private final String name;

    public Restaurant(Long id,String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return  name;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return name + " in " + address;
    }
}
