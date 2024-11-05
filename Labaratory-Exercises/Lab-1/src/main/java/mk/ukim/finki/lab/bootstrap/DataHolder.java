package mk.ukim.finki.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> events = null;
    public static List<EventBooking> eventBookings = null;

    @PostConstruct
    public void init() {
        events = new ArrayList<Event>();
        for(int i=1; i<=10; i++) {
            events.add(new Event("event"+i, "description"+i, i, i));
        }

        eventBookings = new ArrayList<>();
    }
}
