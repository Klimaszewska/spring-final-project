package com.stepniewska.finalproject.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <Event, Integer> {
}
