package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventBookingRepository extends JpaRepository<EventBooking, Long> {
    public List<EventBooking> findEventBookingByAttendeeId(String id);
}
