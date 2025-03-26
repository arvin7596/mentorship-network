package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "mentorship_matches")
public class MentorshipMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;
    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private User mentee;
    private String topic;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MatchStatus status;
    private int progress;
    @Column(name = "mentor_feedback")
    private String mentorFeedback;
    @Column(name = "mentee_feedback")
    private String menteeFeedback;

    public MentorshipMatch() {}

    public MentorshipMatch(Long id, User mentor, User mentee, String topic, MatchStatus status, int progress, String mentorFeedback, String menteeFeedback) {
        this.id = id;   
        this.mentor = mentor;
        this.mentee = mentee;
        this.topic = topic;
        this.status = status;
        this.progress = progress;
        this.mentorFeedback = mentorFeedback; 
        this.menteeFeedback = menteeFeedback;
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

    public User getMentee() {
        return mentee;
    }

    public void setMentee(User mentee) {
        this.mentee = mentee;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getMentorFeedback() {
        return mentorFeedback;
    }

    public void setMentorFeedback(String mentorFeedback) {
        this.mentorFeedback = mentorFeedback;
    }

    public String getMenteeFeedback() {
        return menteeFeedback;
    }

    public void setMenteeFeedback(String menteeFeedback) {
        this.menteeFeedback = menteeFeedback;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MentorshipMatch that = (MentorshipMatch) o;
        return progress == that.progress && Objects.equals(id, that.id) && Objects.equals(mentor, that.mentor) && Objects.equals(mentee, that.mentee) && Objects.equals(topic, that.topic) && Objects.equals(status, that.status) && Objects.equals(mentorFeedback, that.mentorFeedback) && Objects.equals(menteeFeedback, that.menteeFeedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentor, mentee, topic, status, progress, mentorFeedback, menteeFeedback);
    }

    @Override
    public String toString() {
        return "MentorshipMatch{" +
                "id=" + id +
                ", mentor=" + mentor +
                ", mentee=" + mentee +
                ", topic='" + topic + '\'' +
                ", status='" + status + '\'' +
                ", progress=" + progress +
                ", mentorFeedback='" + mentorFeedback + '\'' +
                ", menteeFeedback='" + menteeFeedback + '\'' +
                '}';
    }
}
