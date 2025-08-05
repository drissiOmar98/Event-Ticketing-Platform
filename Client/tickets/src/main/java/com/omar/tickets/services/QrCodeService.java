package com.omar.tickets.services;


import com.omar.tickets.domain.entities.QrCode;
import com.omar.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);


}
