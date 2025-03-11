package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Comment;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.repository.inmemory.InMemoryEventRepository;
import mk.ukim.finki.lab.repository.inmemory.InMemoryLocationRepository;
import mk.ukim.finki.lab.repository.jpa.CommentRepository;
import mk.ukim.finki.lab.repository.jpa.EventRepository;
import mk.ukim.finki.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final CommentRepository commentRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository, CommentRepository commentRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, int rating, Long locationId) {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .filter(event -> {
                    if(locationId != -1) {
                        return event.getLocation().getId() == locationId;
                    }
                    return true;
                })
                .filter(event -> (event.getName().contains(text) || event.getDescription().contains(text)) && event.getPopularityScore() >= rating)

                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> addEvent(String name, String description, double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId).get();
        Event newEvent = new Event(name, description, popularityScore, location);
        return Optional.of(eventRepository.save(newEvent));
    }

    @Override
    public Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            Location location = locationRepository.findById(locationId).get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
            eventRepository.save(event);
        }
        return Optional.of(event);
    }

    @Override
    public void deleteEventById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
         if (event != null) {
             commentRepository.deleteAll(event.getComments());
             eventRepository.deleteById(id);
         }

    }

    @Override
    public Comment addComment(String userId, Long eventId, String comment) {
        Event event = findEventById(eventId).orElse(null);

        if (event != null) {
            Comment newComment = new Comment(userId, event, comment);
            return commentRepository.save(newComment);
        }
        return null;

    }

    @Override
    public boolean checkIfEventExists(String name, Long locationId) {
        return eventRepository.existsEventByNameAndLocation(name, locationRepository.findById(locationId));
    }
}
