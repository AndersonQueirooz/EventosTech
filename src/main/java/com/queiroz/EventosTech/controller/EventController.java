package com.queiroz.EventosTech.controller;
import com.queiroz.EventosTech.domain.event.Event;
import com.queiroz.EventosTech.domain.event.EventRequestDTO;
import com.queiroz.EventosTech.domain.event.EventResponseDTO;
import com.queiroz.EventosTech.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping(consumes = "multipart/form-data") //os dados serão enviados como multipart/form-data
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("uf") String uf,
                                        @RequestParam("remote") boolean remote,
                                        @RequestParam("eventUrl") String eventUrl,
                                        @RequestParam(value = "image", required = false) MultipartFile image) {
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, uf, remote, eventUrl, image);
        Event createdEvent = eventService.createEvent(eventRequestDTO);
        return ResponseEntity.ok(createdEvent);


    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents(@RequestParam (defaultValue = "0")int page, @RequestParam (defaultValue = "10")int size) {
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvent(page, size);
        return ResponseEntity.ok(allEvents);
    }
}
