package mk.ukim.finki.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public List<Event> findALl() {return DataHolder.events;}
    public List<Event> searchEvents(String text, int rating) {
       return DataHolder.events.stream()
               .filter(event -> (event.getName().contains(text) || event.getDescription().contains(text)) && event.getPopularityScore() >= rating)
               .collect(Collectors.toList());
   }

   public List<EventBooking> findAllBookings() {
        return DataHolder.eventBookings;
   }

   public Optional<Event> findEventByName(String name) {
        return DataHolder.events.stream().filter(event -> event.getName().equals(name)).findFirst();
   }

   public Event saveEvent(Event event) {
        DataHolder.events.removeIf(event1 -> event1.getName().equals(event.getName()));
        DataHolder.events.add(event);
        return event;
   }

   public EventBooking placeBooking(EventBooking eventBooking) {
        //DataHolder.eventBookings.removeIf(event -> eventBooking.getEventName().equals(eventBooking.getEventName()));
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
   }
}
