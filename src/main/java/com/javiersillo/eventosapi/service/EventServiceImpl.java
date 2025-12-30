package com.javiersillo.eventosapi.service;

import com.javiersillo.eventosapi.domain.Event;
import com.javiersillo.eventosapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}
