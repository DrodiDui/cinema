package by.kapitonov.cinema.fapi.service.impl;

import by.kapitonov.cinema.fapi.config.UrlConstants;
import by.kapitonov.cinema.fapi.model.Ticket;
import by.kapitonov.cinema.fapi.rest.response.ApiResponse;
import by.kapitonov.cinema.fapi.rest.response.PageResponse;
import by.kapitonov.cinema.fapi.service.EmailSenderService;
import by.kapitonov.cinema.fapi.service.TicketService;
import by.kapitonov.cinema.fapi.service.dto.ticket.ReservedTicketDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final RestTemplate restTemplate;
    private final EmailSenderService emailService;

    public TicketServiceImpl(RestTemplate restTemplate, EmailSenderService emailService) {
        this.restTemplate = restTemplate;
        this.emailService = emailService;
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
        ApiResponse response = restTemplate.patchForObject(
                UrlConstants.TICKET_URL + "/reserved",
                ticketDTO,
                ApiResponse.class);

        if (response != null) {
            emailService.sendNotification(ticketDTO.getUserEmail());
        }

        return response;
    }

    @Override
    public ApiResponse unreservedTicket(List<Long> ticketsId) {
        return restTemplate.patchForObject(
                UrlConstants.TICKET_URL + "/unreserved",
                ticketsId,
                ApiResponse.class);
    }

    @Override
    public ApiResponse unreservedTicket(Long ticketId) {
        return restTemplate.patchForObject(
                UrlConstants.TICKET_URL + "/unreserved/" + ticketId,
                null,
                ApiResponse.class
        );
    }
}
