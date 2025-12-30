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
        Event eventToSave = eventMapper.toEvent(request);
        Event eventSaved = eventService.save(eventToSave);

        EventResponse response = eventMapper.toEventResponse(eventSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
