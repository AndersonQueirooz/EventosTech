package com.queiroz.EventosTech.controller;

import com.queiroz.EventosTech.domain.event.Event;
import com.queiroz.EventosTech.domain.event.EventRequestDTO;
import com.queiroz.EventosTech.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventServices eventServices;


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
        Event createdEvent = eventServices.createEvent(eventRequestDTO);
        return ResponseEntity.ok(createdEvent);


    }
}
