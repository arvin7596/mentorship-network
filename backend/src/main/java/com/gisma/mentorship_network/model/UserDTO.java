package com.gisma.mentorship_network.model;

import java.util.Objects;

public class UserDTO {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;

    public UserDTO() {}
    public UserDTO(Long id, String first_name, String last_name , String email) {  
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;   
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }       

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(first_name, userDTO.first_name) && Objects.equals(last_name, userDTO.last_name) && Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
