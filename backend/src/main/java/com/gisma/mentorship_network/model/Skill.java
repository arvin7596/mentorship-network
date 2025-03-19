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
    private String skill_name;
    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level")
    private SkillLevel skill_level;
    private Long user_id;

    public Skill() {}

    public Skill(Long id, String skill_name, SkillLevel skill_level, Long user_id) {
        this.id = id;
        this.skill_name = skill_name;
        this.skill_level = skill_level;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public SkillLevel getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(SkillLevel skill_level) {
        this.skill_level = skill_level;
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
        return Objects.equals(id, skill.id) && Objects.equals(skill_name, skill.skill_name) && skill_level == skill.skill_level && Objects.equals(user_id, skill.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skill_name, skill_level, user_id);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill_name='" + skill_name + '\'' +
                ", skill_level=" + skill_level +
                ", user_id=" + user_id +
                '}';
    }
}
