package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.repository.EventRepository;
import mk.ukim.finki.lab.repository.LocationRepository;
import mk.ukim.finki.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findALl();
    }

    @Override
    public List<Event> searchEvents(String text, int rating) {
        return eventRepository.searchEvents(text, rating);
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findEventById(id);
    }

    @Override
    public Optional<Event> addEvent(String name, String description, double popularityScore, Long locationId) {
        Location location = locationRepository.findById(locationId);
        Event newEvent = new Event(name, description, popularityScore, location);
        return eventRepository.saveEvent(newEvent);
    }

    @Override
    public Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId) {
        Event event = eventRepository.findEventById(eventId).orElse(null);
        if (event != null) {
            Location location = locationRepository.findById(locationId);
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
        }
        //return eventRepository.saveEvent(event);
        return Optional.of(event);
    }

    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteEventById(id);
    }
}
