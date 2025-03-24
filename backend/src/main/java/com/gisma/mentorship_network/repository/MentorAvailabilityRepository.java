package com.gisma.mentorship_network.repository;
import com.gisma.mentorship_network.model.MentorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.gisma.mentorship_network.model.WeekDay;

public interface MentorAvailabilityRepository extends JpaRepository<MentorAvailability, Long> {
    List<MentorAvailability> findByMentorId(Long mentorId);
    List<MentorAvailability> findByWeekday(WeekDay weekday);
}
