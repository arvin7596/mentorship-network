package com.gisma.mentorship_network.model;

import java.util.Objects;

public class UserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private String city;

    public UserDTO() {}

    public UserDTO(Long id, String first_name, String last_name, String email, String country, String city) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(first_name, userDTO.first_name) && Objects.equals(last_name, userDTO.last_name) && Objects.equals(email, userDTO.email) && Objects.equals(country, userDTO.country) && Objects.equals(city, userDTO.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, country, city);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
