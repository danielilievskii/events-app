package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, int rating);
}
