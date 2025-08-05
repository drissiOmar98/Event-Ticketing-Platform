package com.omar.tickets.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.omar.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketResponseDto {
  private UUID id;
  private TicketStatusEnum status;
  private Double price;
  private String description;
  private String eventName;
  private String eventVenue;
  private LocalDateTime eventStart;
  private LocalDateTime eventEnd;
}
