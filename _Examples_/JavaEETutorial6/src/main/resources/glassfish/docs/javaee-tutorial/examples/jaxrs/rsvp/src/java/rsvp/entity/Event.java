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
package rsvp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author ian
 */
@NamedQuery(name = "rsvp.entity.Event.getAllUpcomingEvents", query = "SELECT e FROM Event e ")
@Entity
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToMany
    protected List<Person> invitees;
    protected String location;
    protected String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventDate;
    @OneToMany(mappedBy = "event")
    private List<Response> responses;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Person owner;

    public Event() {
        this.invitees = new ArrayList<Person>();
        this.responses = new ArrayList<Response>();
    }

    /**
     * Get the value of location
     *
     * @return the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the value of location
     *
     * @param location new value of location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of invitees
     *
     * @return the value of invitees
     */
    public List<Person> getInvitees() {
        return invitees;
    }

    /**
     * Set the value of invitees
     *
     * @param invitees new value of invitees
     */
    public void setInvitees(List<Person> invitees) {
        this.invitees = invitees;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((id != null) ? id.hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event)) {
            return false;
        }

        Event other = (Event) object;

        if (((this.id == null) && (other.id != null))
                || ((this.id != null) && !this.id.equals(other.id))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "rsvp.entity.Event[id=" + id + "]";
    }

    /**
     * @return the responses
     */
    public List<Response> getResponses() {
        return responses;
    }

    /**
     * @param responses the responses to set
     */
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the owner
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
