package com.stepniewska.finalproject.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "event_name", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventName;

    @Column(name = "event_date")
    @NotNull(message = "This field is required")
    private LocalDateTime eventDate;

    @Column(name = "event_address", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventAddress;

    @Column(name = "event_access")
    @NotNull(message = "You need to mark the myEvent as PRIVATE or PUBLIC")
    private EventAccess eventAccess;

    @Column(name = "event_organizer", nullable = false)
    @NotEmpty(message = "This field is required")
    private String eventOrganizer;

}
