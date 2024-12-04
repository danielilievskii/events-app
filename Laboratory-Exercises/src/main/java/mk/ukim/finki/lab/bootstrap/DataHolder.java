package mk.ukim.finki.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.repository.jpa.EventRepository;
import mk.ukim.finki.lab.repository.jpa.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> events = null;
    public static List<EventBooking> eventBookings = null;
    public static List<Location> locations = null;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        locations = new ArrayList<>();
        if(this.locationRepository.count() == 0) {
            locations.add(new Location("Central Library", "123 Main St, Cityville", "200", "A spacious library in the heart of the city."));
            locations.add(new Location("Downtown Conference Center", "456 Elm St, Metropolis", "500", "Modern conference center with high-tech facilities."));
            locations.add(new Location("Community Hall", "789 Maple Ave, Smalltown", "150", "Cozy community hall ideal for small gatherings and events."));
            locations.add(new Location("Seaside Pavilion", "101 Ocean Drive, Beach City", "300", "Scenic pavilion overlooking the sea, perfect for weddings and celebrations."));
            locations.add(new Location("Mountain View Lodge", "202 Alpine Rd, Hilltown", "120", "Rustic lodge with beautiful mountain views, suitable for retreats and conferences."));
            this.locationRepository.saveAll(locations);
        }


        events = new ArrayList<>();
        if(this.eventRepository.count() == 0) {
            events.add(new Event("event"+1, "description"+1, 1, locations.get(0)));
            events.add(new Event("event"+2, "description"+2, 2, locations.get(1)));
            events.add(new Event("event"+3, "description"+3, 3, locations.get(2)));
            events.add(new Event("event"+4, "description"+4, 4, locations.get(3)));
            events.add(new Event("event"+5, "description"+5, 5, locations.get(4)));
            this.eventRepository.saveAll(events);
        }

        eventBookings = new ArrayList<>();
    }
}
