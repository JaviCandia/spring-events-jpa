package com.javiersillo.eventosapi.service;

import com.javiersillo.eventosapi.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event save(Event event);
}
