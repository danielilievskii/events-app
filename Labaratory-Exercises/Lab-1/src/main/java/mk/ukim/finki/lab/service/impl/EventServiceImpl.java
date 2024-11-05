package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.repository.EventRepository;
import mk.ukim.finki.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findALl();
    }

    @Override
    public List<Event> searchEvents(String text, int rating) {
        return eventRepository.searchEvents(text, rating);
    }
}
