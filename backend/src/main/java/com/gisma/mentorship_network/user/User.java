package com.gisma.mentorship_network.user;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Date birth_date;
    private Boolean is_active;
    private Boolean is_mentee;
    private Boolean is_mentor;
    private String location;
    private String phone;
    private Date created_at;

    public User() {
    }

    public User(Integer id, String first_name, String last_name, String email, Date birth_date, Boolean is_active, Boolean is_mentee, Boolean is_mentor, String location, String phone, Date created_at) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birth_date = birth_date;
        this.is_active = is_active;
        this.is_mentee = is_mentee;
        this.is_mentor = is_mentor;
        this.location = location;
        this.phone = phone;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Boolean getIs_mentee() {
        return is_mentee;
    }

    public void setIs_mentee(Boolean is_mentee) {
        this.is_mentee = is_mentee;
    }

    public Boolean getIs_mentor() {
        return is_mentor;
    }

    public void setIs_mentor(Boolean is_mentor) {
        this.is_mentor = is_mentor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(birth_date, user.birth_date) && Objects.equals(is_active, user.is_active) && Objects.equals(is_mentee, user.is_mentee) && Objects.equals(is_mentor, user.is_mentor) && Objects.equals(location, user.location) && Objects.equals(phone, user.phone) && Objects.equals(created_at, user.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, password, birth_date, is_active, is_mentee, is_mentor, location, phone, created_at);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birth_date=" + birth_date +
                ", is_active=" + is_active +
                ", is_mentee=" + is_mentee +
                ", is_mentor=" + is_mentor +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
