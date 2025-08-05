package com.omar.tickets.repositories;

import com.omar.tickets.domain.entities.Event;
import com.omar.tickets.domain.entities.EventStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByOrganizerId(UUID organizerId, Pageable pageable);

    Optional<Event> findByIdAndOrganizerId(UUID id, UUID organizerId);



}
