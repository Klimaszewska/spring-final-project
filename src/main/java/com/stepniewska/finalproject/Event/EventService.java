package com.stepniewska.finalproject.Event;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EventService {
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event findById(int id) {
        return eventRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event update(int id, Event event){
        Event persistedEvent = findById(id);
/*        persistedEvent.setBrand(Event.getBrand());
        persistedEvent.setSpeed(Event.getSpeed());*/
        persistedEvent = eventRepository.save(persistedEvent);
        return persistedEvent;
    }

    public Event delete(int id){
        Event deletedEvent = findById(id);
        eventRepository.delete(deletedEvent);
        return deletedEvent;
    }
}
