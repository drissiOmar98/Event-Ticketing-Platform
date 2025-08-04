package com.omar.tickets.services;


import com.omar.tickets.domain.CreateEventRequest;
import com.omar.tickets.domain.UpdateEventRequest;
import com.omar.tickets.domain.entities.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);


}
