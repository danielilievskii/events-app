package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(Long eventId, String attendeeId, String attendeeName, String attendeeAddress, int numberOfTickets);
    List<EventBooking> findAllBookings();
    List<EventBooking> findBookingsByAttendeeId(String id);
}
