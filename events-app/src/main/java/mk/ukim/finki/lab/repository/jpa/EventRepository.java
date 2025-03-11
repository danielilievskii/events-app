package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    Boolean existsEventByNameAndLocation(String name, Optional<Location> location);
}
