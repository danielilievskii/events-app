package mk.ukim.finki.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.model.Location;
import mk.ukim.finki.lab.service.EventService;
import mk.ukim.finki.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping("/{id}/details")
    public String getEventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.findEventById(id).get();
        model.addAttribute("event", event);
        return "event-details";
    }
    @PostMapping("/add-comment")
    public String addComment(@RequestParam Long eventId,
                             @RequestParam String comment,
                             HttpServletRequest request,
                             Model model) {
        String userId = (String) request.getSession().getId();
        eventService.addComment(userId, eventId, comment);
        return "redirect:/events/" + eventId + "/details";
    }

    @GetMapping()
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        List<Event> events = eventService.listAll();
        model.addAttribute("events", events);
        return "eventsList";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam long locationId,
                            Model model) {
        eventService.addEvent(name, description, popularityScore, locationId);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findEventById(id).orElse(null);
        if (event == null) {
            return "redirect:/events?error='EventNotFound'";
        }
        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("event", event);
        return "add-event";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable String eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam String locationId,
                            Model model) {
        eventService.editEvent(Long.parseLong(eventId), name, description, popularityScore, Long.parseLong(locationId));
        return "redirect:/events";

    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable String id) {
        eventService.deleteEventById(Long.parseLong(id));
        return "redirect:/events";
    }



}
