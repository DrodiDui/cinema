package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Ticket;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.TicketService;
import by.kapitonov.cinema.fapi.service.dto.ticket.ReservedTicketDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketServiceImpl implements TicketService {

    private final RestTemplate restTemplate;

    public TicketServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(UrlConstants.TICKET_URL).build();
    }

    @Override
    public PageResponse<Ticket> getAllUnreservedTicket(int page, int size) {
        return restTemplate.getForObject("?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public PageResponse<Ticket> getAllUserTicket(Long userId, int page, int size) {
        return restTemplate.getForObject("/" + userId + "?page=" + page + "&size=" + size, PageResponse.class);
    }

    @Override
    public ApiResponse reservedTicket(ReservedTicketDTO ticketDTO) {
        return restTemplate.patchForObject("", ticketDTO, ApiResponse.class);
    }

    @Override
    public ApiResponse unreservedTicket(Long ticketId) {
        return restTemplate.patchForObject("/" + ticketId, null, ApiResponse.class);
    }
}
