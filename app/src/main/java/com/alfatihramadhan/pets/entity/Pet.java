package com.alfatihramadhan.pets.entity;

/**
 * Created by Alkha on 6/11/2017.
 * Pet POJO
 */

public class Pet {

    private int id;
    private String name;
    private String breed;
    private int gender;
    private int weight;

    public Pet(int id, String name, String breed, int gender, int weight) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getGender() {
        return gender;
    }

    public int getWeight() {
        return weight;
    }
}
