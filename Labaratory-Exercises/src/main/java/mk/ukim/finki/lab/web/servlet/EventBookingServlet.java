package mk.ukim.finki.lab.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.EventBooking;
import mk.ukim.finki.lab.service.EventBookingService;
import mk.ukim.finki.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(name = "servletEventBooking", urlPatterns = "/servlet/eventBooking")
public class EventBookingServlet extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;
    public final EventBookingService eventBookingService;
    public final EventService eventService;


    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService eventBookingService, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("eventBookings", eventBookingService.findAllBookings());

        springTemplateEngine.process("bookingConfirmation.html", webContext, resp.getWriter());
    }

//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        EventBooking eventBooking = eventBookingService.placeBooking(
//                Long.parseLong(request.getParameter("eventId")),
//                request.getServerName(),
//                request.getLocalAddr(),
//                parseInt(request.getParameter("numTickets"))
//                );
//
//        if (eventBooking != null) {
//            response.sendRedirect("/eventBooking");
//        } else {
//            IWebExchange webExchange = JakartaServletWebApplication
//                    .buildApplication(getServletContext())
//                    .buildExchange(request, response);
//
//            WebContext webContext = new WebContext(webExchange);
//            webContext.setVariable("events", eventService.listAll());
//            webContext.setVariable("error", "Failed to book event");
//            springTemplateEngine.process("eventsList.html", webContext, response.getWriter());
//        }
//    }
}
