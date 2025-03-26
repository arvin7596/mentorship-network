package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Mentorship_Sessions")
public class MentorshipSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mentorship_match_id")
    private Long mentorshipMatchId;

    @Column(name = "scheduled_date")
    private Date scheduledDate;

    @Column(name = "mentor_notes")
    private String mentorNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public MentorshipSession() {}

    public MentorshipSession(Long id, Long mentorshipMatchId, Date scheduledDate, String mentorNotes, SessionStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.mentorshipMatchId = mentorshipMatchId;
        this.scheduledDate = scheduledDate;
        this.mentorNotes = mentorNotes;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMentorshipMatchId() {
        return mentorshipMatchId;
    }

    public void setMentorshipMatchId(Long mentorshipMatchId) {
        this.mentorshipMatchId = mentorshipMatchId;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public String getMentorNotes() {
        return mentorNotes;
    }

    public void setMentorNotes(String mentorNotes) {
        this.mentorNotes = mentorNotes;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
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
        MentorshipSession that = (MentorshipSession) o;
        return Objects.equals(id, that.id) && Objects.equals(mentorshipMatchId, that.mentorshipMatchId) && Objects.equals(scheduledDate, that.scheduledDate) && Objects.equals(mentorNotes, that.mentorNotes) && status == that.status && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentorshipMatchId, scheduledDate, mentorNotes, status, createdAt);
    }

    @Override
    public String toString() {
        return "MentorshipSession{" +
                "id=" + id +
                ", mentorshipMatchId=" + mentorshipMatchId +
                ", scheduledDate=" + scheduledDate +
                ", mentorNotes='" + mentorNotes + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
