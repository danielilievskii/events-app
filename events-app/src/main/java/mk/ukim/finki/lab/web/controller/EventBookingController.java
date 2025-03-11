package mk.ukim.finki.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.service.EventBookingService;
import mk.ukim.finki.lab.service.EventService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    public final EventBookingService eventBookingService;
    public final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getEventBookingPage(HttpServletRequest request, Model model) {
        String attendeeId = request.getSession().getId();
        model.addAttribute("eventBookings", eventBookingService.findBookingsByAttendeeId(attendeeId));

        model.addAttribute("bodyContent", "bookingConfirmation");
        return "master-template";

    }

    @PostMapping
    public String placeBooking(@RequestParam Long eventId,
                               @RequestParam String attendeeName,
                               @RequestParam int numTickets,
                               HttpServletRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            String attendeeId = request.getSession().getId();
            String attendeeAddress = request.getLocalAddr();
            eventBookingService.placeBooking(eventId, attendeeId, attendeeName, attendeeAddress, numTickets);
            return "redirect:/eventBooking";
        }
        else {
            return "redirect:/login";
        }


    }


}
