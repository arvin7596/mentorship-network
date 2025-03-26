package com.gisma.mentorship_network.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;



@Entity
@Table(name = "session_feedbacks")
public class SessionFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private MentorshipSession sessionId;

    @Column(name = "mentee_feedback")
    private String menteeFeedback;

    @Column(name = "rate")
    private double rate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public SessionFeedback() {
    }

    public SessionFeedback(Long id, MentorshipSession sessionId, String menteeFeedback, double rate, LocalDateTime createdAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.menteeFeedback = menteeFeedback;
        this.rate = rate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MentorshipSession getSessionId() {
        return sessionId;
    }

    public void setSessionId(MentorshipSession sessionId) {
        this.sessionId = sessionId;
    }

    public String getMenteeFeedback() {
        return menteeFeedback;
    }

    public void setMenteeFeedback(String menteeFeedback) {
        this.menteeFeedback = menteeFeedback;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessionFeedback that = (SessionFeedback) o;
        return Double.compare(rate, that.rate) == 0 && Objects.equals(id, that.id) && Objects.equals(sessionId, that.sessionId) && Objects.equals(menteeFeedback, that.menteeFeedback) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, menteeFeedback, rate, createdAt);
    }

    @Override
    public String toString() {
        return "SessionFeedback{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", menteeFeedback='" + menteeFeedback + '\'' +
                ", rate=" + rate +
                ", createdAt=" + createdAt +
                '}';
    }
}
