package com.gti.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class Person {

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Sur name is required")
    private String surName;
    @NotBlank(message = "PPSN is required")
    @Column(unique = true)
    private String ppsn;
    private String gender;
    @Email
    @NotBlank(message = "Email Address is required")
    private String email;
    @Size(min = 10, max = 15)
    private String phoneNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String eircode;
    private LocalDate dateOfBirth;

    // Constructors
    public Person() {
        //default Constructor
    }

    // Full Constructor
    public Person(String firstName, String surName, String ppsn, String gender, String email,
                  String phoneNumber, String addressLineOne, String addressLineTwo, String city, String eircode, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.surName = surName;
        this.ppsn = ppsn;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.eircode = eircode;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPpsn() {
        return ppsn;
    }

    public void setPpsn(String ppsn) {
        this.ppsn = ppsn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // for polymorphism later on
    public abstract void displayInfo();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(surName, person.surName) && Objects.equals(ppsn, person.ppsn)
                && Objects.equals(gender, person.gender) && Objects.equals(email, person.email) && Objects.equals(phoneNumber, person.phoneNumber)
                && Objects.equals(addressLineOne, person.addressLineOne) && Objects.equals(addressLineTwo, person.addressLineTwo)
                && Objects.equals(city, person.city) && Objects.equals(eircode, person.eircode) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surName, ppsn, gender, email, phoneNumber, addressLineOne, addressLineTwo, city, eircode, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", ppsn='" + ppsn + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", addressLineOne='" + addressLineOne + '\'' +
                ", addressLineTwo='" + addressLineTwo + '\'' +
                ", city='" + city + '\'' +
                ", eircode='" + eircode + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
