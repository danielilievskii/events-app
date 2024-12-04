package mk.ukim.finki.lab.repository.inmemory;

import mk.ukim.finki.lab.bootstrap.DataHolder;
import mk.ukim.finki.lab.model.Comment;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryEventRepository {
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
    public Optional<Event> findEventById(Long id) {
        return DataHolder.events.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

   public void deleteEventById(Long id) {
        DataHolder.events.removeIf(event -> event.getId().equals(id));
   }

   public Optional<Event> saveEvent(Event event) {
        DataHolder.events.removeIf(event1 -> event1.getId().equals(event.getId()));
        DataHolder.events.add(event);
        return Optional.of(event);
   }

   public EventBooking placeBooking(EventBooking eventBooking) {
        //DataHolder.eventBookings.removeIf(event -> eventBooking.getEventName().equals(eventBooking.getEventName()));
        DataHolder.eventBookings.add(eventBooking);
        return eventBooking;
   }

   public Comment placeComment(Comment comment) {
        Event event = findEventById(comment.getEvent().getId()).orElse(null);
        if (event != null) {
            event.getComments().add(comment);
        }
        return comment;
   }
}
