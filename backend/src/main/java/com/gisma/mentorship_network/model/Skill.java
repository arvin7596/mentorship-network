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

    @Column(name = "user_id")
    private Long userId;

    public Skill() {}

    public Skill(Long id, String name, SkillLevel level, Long user_id) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.userId = user_id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) && Objects.equals(name, skill.name) && level == skill.level && Objects.equals(userId, skill.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, userId);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", userId=" + userId +
                '}';
    }
}
