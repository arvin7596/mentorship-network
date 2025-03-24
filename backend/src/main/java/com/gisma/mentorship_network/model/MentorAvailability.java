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

    @Column(name = "mentor_id")  
    private Long mentorId;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday")
    private WeekDay weekday;

    public MentorAvailability(){}

    public MentorAvailability(Long id, Long mentor_id, LocalTime startTime, LocalTime endTime, WeekDay weekday) {
        this.id = id;
        this.mentorId = mentor_id;
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

    public Long getMentor_id() {
        return mentorId;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentorId = mentor_id;
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
        return Objects.equals(id, that.id) && Objects.equals(mentorId, that.mentorId) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && weekday == that.weekday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentorId, startTime, endTime, weekday);
    }

    @Override
    public String toString() {
        return "MentorAvailability{" +
                "id=" + id +
                ", mentor_id=" + mentorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", weekday=" + weekday +
                '}';
    }
}
