package by.kapitonov.cinema.backend.service.mapper;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Ticket;
import by.kapitonov.cinema.backend.service.dto.ticket.TicketDTO;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        if (ticket == null) {
            throw new ModelNotFoundException("Ticket hasn't been found");
        }
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setRowsNumber(ticket.getRowsNumber());
        ticketDTO.setNumberSeatsPerRow(ticket.getNumberSeatsPerRow());
        ticketDTO.setFilmName(ticket.getFilmSession().getFilmName());
        ticketDTO.setHallName(ticket.getFilmSession().getHall().getHallName());
        ticketDTO.setShowTime(ticket.getFilmSession().getShowTime());
        ticketDTO.setDuration(ticket.getFilmSession().getFilm().getDuration());
        return ticketDTO;
    }

}
