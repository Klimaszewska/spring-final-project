package com.stepniewska.finalproject.Event;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/unauth/event/{id}")
    public Event getById(@PathVariable("id") int id) {
        return eventService.findById(id);
    }

    @PostMapping("/unauth/event")
    public Event create(@RequestBody @Valid Event event) {
        return eventService.create(event);
    }

    @GetMapping("/unauth/event")
    public List<Event> getAll() {
        return eventService.findAll();
    }

    @PutMapping("/unauth/event/{id}")
    public Event update(@PathVariable("id") int id, @RequestBody @Valid Event event) {
        return eventService.update(id, event);
    }

    @DeleteMapping("/unauth/event/{id}")
    public Event delete(@PathVariable("id") int id) {
        return eventService.delete(id);
    }
}
