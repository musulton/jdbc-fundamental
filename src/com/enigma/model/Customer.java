package com.enigma.model;

public class Customer {
    private Integer id;
    private String name, address, birthDate, status, phone;
    public Customer(Integer id, String name, String address, String birthDate, String status, String phone) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.address = address;
    }

    public Customer(String name, String address, String birthDate, String status, String phone) {
        this.birthDate = birthDate;
        this.name = name;
        this.status = status;
        this.phone = phone;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "com.enigma.model.Customer{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
