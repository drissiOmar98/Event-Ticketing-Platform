package com.omar.tickets.mappers;


import com.omar.tickets.domain.CreateEventRequest;
import com.omar.tickets.domain.CreateTicketTypeRequest;
import com.omar.tickets.domain.UpdateEventRequest;
import com.omar.tickets.domain.dtos.*;
import com.omar.tickets.domain.entities.Event;
import com.omar.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);



}
