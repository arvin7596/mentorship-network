package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "mentorship_matches")
public class MentorshipMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mentor_id;
    private Long mentee_id;
    private String topic;
    private String status;
    private int progress;
    private String mentor_feedback;
    private String mentee_feedback;

    public MentorshipMatch() {}

    public MentorshipMatch(Long id, Long mentor_id, Long mentee_id, String topic, String status, int progress, String mentor_feedback, String mentee_feedback) {
        this.id = id;   
        this.mentor_id = mentor_id;
        this.mentee_id = mentee_id;
        this.topic = topic;
        this.status = status;
        this.progress = progress;
        this.mentor_feedback = mentor_feedback; 
        this.mentee_feedback = mentee_feedback;
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

    public Long getMentee_id() {
        return mentee_id;
    }

    public void setMentee_id(Long mentee_id) {
        this.mentee_id = mentee_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getMentor_feedback() {
        return mentor_feedback;
    }

    public void setMentor_feedback(String mentor_feedback) {
        this.mentor_feedback = mentor_feedback;
    }

    public String getMentee_feedback() {
        return mentee_feedback;
    }

    public void setMentee_feedback(String mentee_feedback) {
        this.mentee_feedback = mentee_feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MentorshipMatch that = (MentorshipMatch) o;
        return progress == that.progress && Objects.equals(id, that.id) && Objects.equals(mentor_id, that.mentor_id) && Objects.equals(mentee_id, that.mentee_id) && Objects.equals(topic, that.topic) && Objects.equals(status, that.status) && Objects.equals(mentor_feedback, that.mentor_feedback) && Objects.equals(mentee_feedback, that.mentee_feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentor_id, mentee_id, topic, status, progress, mentor_feedback, mentee_feedback);
    }

    @Override
    public String toString() {
        return "MentorshipMatch{" +
                "id=" + id +
                ", mentor_id=" + mentor_id +
                ", mentee_id=" + mentee_id +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                ", mentor_feedback='" + mentor_feedback + '\'' +
                ", mentee_feedback='" + mentee_feedback + '\'' +
                '}';
    }
}
