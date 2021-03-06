package by.kapitonov.cinema.fapi.rest.controller;

import by.kapitonov.cinema.fapi.model.Ticket;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.TicketService;
import by.kapitonov.cinema.fapi.service.dto.ticket.ReservedTicketDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("{id}/all")
    public ResponseEntity getAll(
            @PathVariable(name = "id") Long sessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<Ticket> ticket = ticketService.getAllUnreservedTicket(sessionId, page, size);

        return new ResponseEntity(ticket, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserTickets(
            @PathVariable(name = "id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResponse<Ticket> ticket = ticketService.getAllUserTicket(userId, page, size);

        return new ResponseEntity(ticket, HttpStatus.OK);
    }

    @PutMapping("/reserved")
    public ResponseEntity<ApiResponse> reserved(@RequestBody ReservedTicketDTO ticketDTO) {

        ApiResponse response = ticketService.reservedTicket(ticketDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/unreserved")
    public ResponseEntity<ApiResponse> unreserved(@RequestBody List<Long> ticketsId) {

        ApiResponse response = ticketService.unreservedTicket(ticketsId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/unreserved/{id}")
    public ResponseEntity<ApiResponse> unreserved(@PathVariable(name = "id") Long ticketId) {

        ApiResponse response = ticketService.unreservedTicket(ticketId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
