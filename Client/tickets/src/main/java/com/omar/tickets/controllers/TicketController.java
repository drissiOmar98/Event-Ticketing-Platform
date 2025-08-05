package com.omar.tickets.controllers;


import java.time.Duration;
import java.util.UUID;

import com.omar.tickets.domain.dtos.GetTicketResponseDto;
import com.omar.tickets.domain.dtos.ListTicketResponseDto;
import com.omar.tickets.mappers.TicketMapper;
import com.omar.tickets.services.QrCodeService;
import com.omar.tickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.omar.tickets.util.JwtUtil.parseUserId;

@RestController
@RequestMapping(path = "/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketService ticketService;
  private final TicketMapper ticketMapper;


  @GetMapping
  public Page<ListTicketResponseDto> listTickets(
      @AuthenticationPrincipal Jwt jwt,
      Pageable pageable
  ) {
    return ticketService.listTicketsForUser(
        parseUserId(jwt),
        pageable
    ).map(ticketMapper::toListTicketResponseDto);
  }



}
