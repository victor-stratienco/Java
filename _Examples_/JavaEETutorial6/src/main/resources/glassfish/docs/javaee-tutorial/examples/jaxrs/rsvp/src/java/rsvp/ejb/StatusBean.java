/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rsvp.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import rsvp.entity.Event;
import rsvp.entity.Person;
import rsvp.entity.Response;


/**
 *
 * @author ian
 */
@Stateless
@Named
@Path("/status/{eventId}/")
public class StatusBean {
    @PersistenceContext
    private EntityManager em;
    private List<Event> allCurrentEvents;
    private Logger logger = Logger.getLogger("rsvp.ejb.StatusBean");

    @GET
    @Produces("text/html")
    public String getResponse(@PathParam("eventId")
    Long eventId) {
        StringBuffer output = new StringBuffer();
        Event event = em.find(Event.class, eventId);
        output.append(
                "<html><head><title>Current responses for " + event.getName()
                + "</title>"
                + "<link href=\"/rsvp/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />"
                + "</head>");
        output.append(
                "<body><h1>Current responses for " + event.getName() + "</h1>");
        output.append("<p><b>Location: " + event.getLocation() + "</p>");
        output.append("<p>");
        output.append("<table>");
        output.append("<tr>");
        output.append("<th>Name</th>");
        output.append("<th>Response</th>");
        output.append("</tr>");

        Iterator<Response> i = event.getResponses()
                                    .iterator();

        while (i.hasNext()) {
            Response response = i.next();
            Person person = response.getPerson();
            output.append("<tr>");
            output.append(
                    "<td><a href=\"/rsvp/resources/" + event.getId() + "/"
                    + person.getId() + "\">" + person.getFirstName() + " "
                    + person.getLastName() + "</a></td>");
            output.append("<td>" + response.getResponseText() + "</td>");
            output.append("</tr>");
        }

        output.append("</table></p>");
        output.append(
                "<p><a href=\"/rsvp/faces/index.xhtml\">Back to main page.</a>");
        output.append("</body></html>");

        return output.toString();
    }

    public List<Event> getAllCurrentEvents() {
        logger.info("Calling getAllCurrentEvents");
        this.allCurrentEvents = (List<Event>) em.createNamedQuery(
                    "rsvp.entity.Event.getAllUpcomingEvents")
                                                .getResultList();

        if (this.allCurrentEvents.equals(null)) {
            logger.warning("No current events!");
        }

        return this.allCurrentEvents;
    }

    public void setAllCurrentEvents(List<Event> events) {
        this.allCurrentEvents = events;
    }
}
