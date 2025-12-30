package com.javiersillo.eventosapi.repository;

import com.javiersillo.eventosapi.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
