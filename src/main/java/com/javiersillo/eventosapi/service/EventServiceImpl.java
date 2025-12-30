package com.javiersillo.eventosapi.service;

import com.javiersillo.eventosapi.domain.Event;
import com.javiersillo.eventosapi.exception.ResourceNotFoundException;
import com.javiersillo.eventosapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Evento no encontrado con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        Event eventToDelete = this.findById(id);
        eventRepository.delete(eventToDelete);
    }
}
