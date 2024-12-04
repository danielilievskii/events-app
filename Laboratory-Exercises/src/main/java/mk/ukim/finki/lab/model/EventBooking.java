package mk.ukim.finki.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EventBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventBookingId;

    @ManyToOne
    private Event event;
    private String attendeeId;
    private String attendeeName;
    private String attendeeAddress;
    private int numberOfTickets;

    public EventBooking(Event event, String attendeeId, String attendeeName, String attendeeAddress, int numberOfTickets) {
        this.event = event;
        this.attendeeId = attendeeId;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
    }

}
