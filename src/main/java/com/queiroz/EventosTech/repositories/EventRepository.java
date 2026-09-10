package com.queiroz.EventosTech.repositories;

import com.queiroz.EventosTech.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository  extends JpaRepository<Event, UUID> {
}
