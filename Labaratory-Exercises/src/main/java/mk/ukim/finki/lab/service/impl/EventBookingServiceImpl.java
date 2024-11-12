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
    public EventBooking placeBooking(Long eventId, String attendeeId, String attendeeName, String attendeeAddress, int numberOfTickets) {
        Event event = eventRepository.findEventById(eventId).orElse(null);

        if(event != null && event.getAvailableTickets() >= numberOfTickets) {
            EventBooking newEventBooking = new EventBooking(event.getName(), attendeeId, attendeeName, attendeeAddress, numberOfTickets);
            event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
            eventRepository.placeBooking(newEventBooking);
            return newEventBooking;
        } else return null;

    }

    @Override
    public List<EventBooking> findAllBookings() {
        return eventRepository.findAllBookings();
    }

    @Override
    public List<EventBooking> findBookingsByAttendeeId(String id) {
        return eventRepository.findAllBookings().stream().filter(eventBooking -> eventBooking.getAttendeeId().equals(id)).toList();
    }
}
