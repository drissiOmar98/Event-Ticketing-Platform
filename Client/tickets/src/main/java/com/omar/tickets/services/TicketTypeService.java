package com.omar.tickets.services;


import com.omar.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface TicketTypeService {
  Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
}
