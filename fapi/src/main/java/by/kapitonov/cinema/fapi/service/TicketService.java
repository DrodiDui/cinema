package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.model.Ticket;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.dto.ticket.ReservedTicketDTO;

import java.util.List;

public interface TicketService {

    PageResponse<Ticket> getAllUnreservedTicket(Long sessionId, int page, int size);
    PageResponse<Ticket> getAllUserTicket(Long userId, int page, int size);

    ApiResponse reservedTicket(ReservedTicketDTO ticketDTO);
    ApiResponse unreservedTicket(List<Long> ticketsId);
}
