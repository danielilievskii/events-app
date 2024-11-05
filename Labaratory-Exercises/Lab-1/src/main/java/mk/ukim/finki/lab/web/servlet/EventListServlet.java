package mk.ukim.finki.lab.web.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.Event;
import mk.ukim.finki.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("events", eventService.listAll());
        templateEngine.process("eventsList.html", context, resp.getWriter());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String keyword = req.getParameter("keyword");
        int rating;
        if(!req.getParameter("rating").isEmpty()) {
            rating = Integer.parseInt(req.getParameter("rating"));
        } else rating = 0;

        List<Event> filteredEvents = eventService.searchEvents(keyword, rating);

        context.setVariable("events", filteredEvents);
        templateEngine.process("eventsList.html", context, resp.getWriter());



    }
}
