package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.TicketService;
import by.kapitonov.cinema.backend.service.dto.ticket.UpdateTicketDTO;
import by.kapitonov.cinema.backend.service.dto.ticket.TicketDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketRestController {

    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("")
    public ResponseEntity getAll(Pageable pageable) {

        Page<TicketDTO> ticketDTOS = ticketService.getAllUnreservedTickets(pageable);

        return new ResponseEntity(ticketDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserTickets(@PathVariable(name = "id") Long userId, Pageable pageable) {

        Page<TicketDTO> ticketDTOS = ticketService.getUserTickets(userId, pageable);

        return new ResponseEntity(ticketDTOS, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> reservedTicket(@RequestBody UpdateTicketDTO ticketDTO) {

        ticketService.reservedTicket(ticketDTO);

        return new ResponseEntity<>(new ApiResponse("Ticket successfully reserved"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> unreservedTicket(@PathVariable(name = "id") Long ticketId) {

        ticketService.unreserved(ticketId);

        return new ResponseEntity<>(new ApiResponse("Ticket successfully unreserved"), HttpStatus.OK);
    }
}
