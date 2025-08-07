package com.omar.tickets.services;


import com.omar.tickets.domain.entities.TicketValidation;

import java.util.UUID;

public interface TicketValidationService {
  TicketValidation validateTicketByQrCode(UUID qrCodeId);
  TicketValidation validateTicketManually(UUID ticketId);
}
