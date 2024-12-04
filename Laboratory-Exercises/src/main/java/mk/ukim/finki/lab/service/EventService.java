package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Comment;
import mk.ukim.finki.lab.model.Event;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    List<Event> searchEvents(String text, int rating, Long locationId);

    Optional<Event> findEventById(Long id);

    Optional<Event> addEvent(String name, String description, double popularityScore, Long locationId);

    Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId);

    void deleteEventById(Long id);

    Comment addComment(String userId, Long eventId, String comment);
}
