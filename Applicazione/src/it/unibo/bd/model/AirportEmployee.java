package it.unibo.bd.model;

import java.util.Optional;

public class AirportEmployee implements Employee{
    private String code;
    private String name;
    private String surname;
    private Gender gender;
    private String city;
    private String adress;
    private String role;
    private Optional<String> specificRole;
    
    public AirportEmployee(String code, String name, String surname, Gender gender, String city, String adress, String role, Optional<String> specificRole) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.city = city;
        this.adress = adress;
        this.role = role;
        this.specificRole = specificRole;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getAdress() {
        return this.adress;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    public Optional<String> getSpecificRole() {
        return this.specificRole;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public void setSpecificRole(Optional<String> specificRole) {
        this.specificRole = specificRole;
    }
}
