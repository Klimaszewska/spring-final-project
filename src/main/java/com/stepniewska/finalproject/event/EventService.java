package com.stepniewska.finalproject.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EventService {

   private EventRepository eventRepository;

   public Event createEvent(Event event){
       return eventRepository.save(event);
   }

   public Event findEventById(Integer eventId){
       return eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
   }

}
