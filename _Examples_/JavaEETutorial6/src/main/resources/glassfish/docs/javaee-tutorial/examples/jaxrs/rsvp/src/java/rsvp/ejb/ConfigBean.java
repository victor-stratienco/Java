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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rsvp.entity.Event;
import rsvp.entity.Person;
import rsvp.entity.Response;
import rsvp.util.ResponseEnum;


/**
 *
 * @author ian
 */
@Singleton
@Startup
public class ConfigBean {
    private static Logger logger = Logger.getLogger("rsvp.ejb.ConfigBean");
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        // create the event owner
        Person dad = new Person();
        dad.setFirstName("James");
        dad.setLastName("Gosling");
        em.persist(dad);

        // create the event
        Event event = new Event();
        event.setName("Duke's Birthday Party");
        event.setLocation("Top of the Mark");

        Calendar cal = new GregorianCalendar(2010, Calendar.MAY, 23, 19, 0);
        event.setEventDate(cal.getTime());
        em.persist(event);

        // set the relationships
        dad.getOwnedEvents()
           .add(event);
        dad.getEvents()
           .add(event);
        event.setOwner(dad);
        event.getInvitees()
             .add(dad);

        Response dadsResponse = new Response(
                    event,
                    dad,
                    ResponseEnum.ATTENDING);
        em.persist(dadsResponse);
        event.getResponses()
             .add(dadsResponse);

        // create some invitees
        Person larry = new Person();
        larry.setFirstName("Lawrence");
        larry.setLastName("Ellison");
        em.persist(larry);

        Person scott = new Person();
        scott.setFirstName("Scott");
        scott.setLastName("McNealy");
        em.persist(scott);

        // set the relationships
        event.getInvitees()
             .add(larry);
        larry.getEvents()
             .add(event);

        Response larrysResponse = new Response(event, larry);
        em.persist(larrysResponse);
        event.getResponses()
             .add(larrysResponse);
        larry.getResponses()
             .add(larrysResponse);

        event.getInvitees()
             .add(scott);
        scott.getEvents()
             .add(event);

        Response scottsResponse = new Response(event, scott);
        em.persist(scottsResponse);
        event.getResponses()
             .add(scottsResponse);
        scott.getResponses()
             .add(scottsResponse);
    }
}
