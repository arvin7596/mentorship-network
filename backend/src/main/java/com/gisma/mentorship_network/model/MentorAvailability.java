package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Mentor_Availability")
public class MentorAvailability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mentor_id;
    private Timestamp startDate;
    private Timestamp endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private WeekDay weekDay;

    public MentorAvailability(){}

    public MentorAvailability(Long id, Long mentor_id, Timestamp startDate, Timestamp endDate, WeekDay weekDay) {
        this.id = id;
        this.mentor_id = mentor_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekDay = weekDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentor_id = mentor_id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MentorAvailability that = (MentorAvailability) o;
        return Objects.equals(id, that.id) && Objects.equals(mentor_id, that.mentor_id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && weekDay == that.weekDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentor_id, startDate, endDate, weekDay);
    }

    @Override
    public String toString() {
        return "MentorAvailability{" +
                "id=" + id +
                ", mentor_id=" + mentor_id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", weekDay=" + weekDay +
                '}';
    }
}
