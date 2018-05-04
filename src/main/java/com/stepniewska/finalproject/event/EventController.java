package com.stepniewska.finalproject.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class EventController {

    private EventService eventService;

    @GetMapping("/unauth/user/{id}")
    public Event getById(@PathVariable("id") Integer id) {
        return eventService.findEventById(id);
    }

    @PostMapping("/unauth/user")
    public Event create(@RequestBody @Valid Event event) {
        return eventService.createEvent(event);
    }
}
