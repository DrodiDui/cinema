package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Ticket;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.TicketService;
import by.kapitonov.cinema.fapi.service.dto.ticket.ReservedTicketDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final RestTemplate restTemplate;

    public TicketServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public PageResponse<Ticket> getAllUnreservedTicket(Long sessionId, int page, int size) {
        return restTemplate.getForObject(UrlConstants.TICKET_URL + "/" + sessionId + "/all?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public PageResponse<Ticket> getAllUserTicket(Long userId, int page, int size) {
        return restTemplate.getForObject(UrlConstants.TICKET_URL + "/" + userId + "?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public ApiResponse reservedTicket(ReservedTicketDTO ticketDTO) {
        return restTemplate.patchForObject(UrlConstants.TICKET_URL + "/reserved", ticketDTO, ApiResponse.class);
    }

    @Override
    public ApiResponse unreservedTicket(List<Long> ticketsId) {
        return restTemplate.patchForObject(UrlConstants.TICKET_URL + "/unreserved", ticketsId, ApiResponse.class);
    }
}
