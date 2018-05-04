package com.stepniewska.finalproject.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    @Column(name = "event_date", nullable = false)
    @NotEmpty(message = "This field is required")
    private LocalDateTime eventDate;

    @Column(name = "event_name", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventName;

    @Column(name = "event_address", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventAddress;

    @Column(name = "event_access", nullable = false)
    @NotEmpty(message = "You need to mark the event as PRIVATE or PUBLIC")
    private EventAccess eventAccess;

    @Column(name = "event_organizer", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventOrganizer;

}
