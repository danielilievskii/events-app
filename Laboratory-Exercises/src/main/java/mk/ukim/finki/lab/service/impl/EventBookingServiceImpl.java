package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.repository.inmemory.InMemoryEventRepository;
import mk.ukim.finki.lab.repository.jpa.EventBookingRepository;
import mk.ukim.finki.lab.repository.jpa.EventRepository;
import mk.ukim.finki.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventRepository eventRepository;
    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventRepository eventRepository, EventBookingRepository eventBookingRepository) {
        this.eventRepository = eventRepository;
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(Long eventId, String attendeeId, String attendeeName, String attendeeAddress, int numberOfTickets) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if(event != null && event.getAvailableTickets() >= numberOfTickets) {
            EventBooking newEventBooking = new EventBooking(event, attendeeId, attendeeName, attendeeAddress, numberOfTickets);
            eventBookingRepository.save(newEventBooking);

            event.setAvailableTickets(event.getAvailableTickets() - numberOfTickets);
            eventRepository.save(event);

            return newEventBooking;
        } else return null;

    }

    @Override
    public List<EventBooking> findAllBookings() {
        return eventBookingRepository.findAll();
    }

    @Override
    public List<EventBooking> findBookingsByAttendeeId(String id) {
        return eventBookingRepository.findEventBookingByAttendeeId(id);
    }
}
