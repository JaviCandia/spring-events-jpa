package com.javiersillo.eventosapi.controller;

import com.javiersillo.eventosapi.domain.Event;
import com.javiersillo.eventosapi.dto.EventCreateRequest;
import com.javiersillo.eventosapi.dto.EventResponse;
import com.javiersillo.eventosapi.mapper.EventMapper;
import com.javiersillo.eventosapi.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public List<EventResponse> getAllEvents() {
        List<Event> events = eventService.findAll();

        return eventMapper.toEventResponses(events);
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventCreateRequest request) {
        Event newEvent = eventMapper.toEvent(request);
        Event savedEvent = eventService.save(newEvent);
        EventResponse response = eventMapper.toEventResponse(savedEvent);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        EventResponse eventResponse = eventMapper.toEventResponse(event);

        return ResponseEntity.ok(eventResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id, @Valid @RequestBody EventCreateRequest request) {
        Event existingEvent = eventService.findById(id);
        eventMapper.updateEvent(request, existingEvent);
        Event updatedEvent = eventService.save(existingEvent);

        return ResponseEntity.ok(eventMapper.toEventResponse(updatedEvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
