package com.javiersillo.eventosapi.mapper;

import com.javiersillo.eventosapi.domain.Event;
import com.javiersillo.eventosapi.dto.EventCreateRequest;
import com.javiersillo.eventosapi.dto.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEvent(EventCreateRequest request);

    EventResponse toEventResponse(Event event);

    List<EventResponse> toEventResponses(List<Event> events);

    void updateEvent(EventCreateRequest request, @MappingTarget Event event);
}
