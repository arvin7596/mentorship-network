package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "Mentor_Availability")
public class MentorAvailability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mentor_id")
    private User mentor;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private WeekDay weekday;

    public MentorAvailability(){}

    public MentorAvailability(Long id, User mentor, LocalTime startTime, LocalTime endTime, WeekDay weekday) {
        this.id = id;
        this.mentor = mentor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = weekday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getMentor() {
        return mentor;
    }

    public void setMentor(User mentor) {
        this.mentor = mentor;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MentorAvailability that = (MentorAvailability) o;
        return Objects.equals(id, that.id) && Objects.equals(mentor, that.mentor) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && weekday == that.weekday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentor, startTime, endTime, weekday);
    }

    @Override
    public String toString() {
        return "MentorAvailability{" +
                "weekday=" + weekday +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", mentor=" + mentor +
                ", id=" + id +
                '}';
    }
}
