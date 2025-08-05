package com.omar.tickets.mappers;


import com.omar.tickets.domain.dtos.GetTicketResponseDto;
import com.omar.tickets.domain.dtos.ListTicketResponseDto;
import com.omar.tickets.domain.dtos.ListTicketTicketTypeResponseDto;
import com.omar.tickets.domain.entities.Ticket;
import com.omar.tickets.domain.entities.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

  ListTicketTicketTypeResponseDto toListTicketTicketTypeResponseDto(TicketType ticketType);

  ListTicketResponseDto toListTicketResponseDto(Ticket ticket);


}
