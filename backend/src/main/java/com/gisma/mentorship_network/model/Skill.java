package com.gisma.mentorship_network.model;
import jakarta.persistence.*;
import java.util.Objects;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "User_Skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private SkillLevel level;
    private Long user_id;

    public Skill() {}

    public Skill(Long id, String name, SkillLevel level, Long user_id) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(name, skill.name) && level == skill.level && Objects.equals(user_id, skill.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, user_id);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", user_id=" + user_id +
                '}';
    }
}
