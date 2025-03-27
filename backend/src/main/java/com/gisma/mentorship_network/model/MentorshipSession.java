package com.gisma.mentorship_network.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Mentorship_Sessions")
public class MentorshipSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mentorship_match_id")
    private MentorshipMatch mentorshipMatch;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "mentor_notes")
    private String mentorNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public MentorshipSession() {}

    public MentorshipSession(Long id, MentorshipMatch mentorshipMatch, LocalDateTime scheduledDate, String mentorNotes, SessionStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.mentorshipMatch = mentorshipMatch;
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

    public MentorshipMatch getMentorshipMatch() {
        return mentorshipMatch;
    }

    public void setMentorshipMatch(MentorshipMatch mentorshipMatch) {
        this.mentorshipMatch = mentorshipMatch;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
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
        return Objects.equals(id, that.id) && Objects.equals(mentorshipMatch, that.mentorshipMatch) && Objects.equals(scheduledDate, that.scheduledDate) && Objects.equals(mentorNotes, that.mentorNotes) && status == that.status && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mentorshipMatch, scheduledDate, mentorNotes, status, createdAt);
    }

    @Override
    public String toString() {
        return "MentorshipSession{" +
                "id=" + id +
                ", mentorshipMatch=" + mentorshipMatch +
                ", scheduledDate=" + scheduledDate +
                ", mentorNotes='" + mentorNotes + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
