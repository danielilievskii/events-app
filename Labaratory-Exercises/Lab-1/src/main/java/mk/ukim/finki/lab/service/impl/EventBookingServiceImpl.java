package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.repository.EventRepository;
import mk.ukim.finki.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking newEventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        Event event = eventRepository.findEventByName(eventName).get();
        if(event != null && event.getAvailableTickets() >= newEventBooking.getNumberOfTickets()) {
            event.setAvailableTickets(event.getAvailableTickets() - newEventBooking.getNumberOfTickets());
            eventRepository.saveEvent(event);
            eventRepository.placeBooking(newEventBooking);
            return newEventBooking;
        } else return null;

    }

    @Override
    public List<EventBooking> findAllBookings() {
        return eventRepository.findAllBookings();
    }
}
