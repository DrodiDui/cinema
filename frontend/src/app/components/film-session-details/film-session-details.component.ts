import { Component, OnInit } from '@angular/core';
import {FilmSessionService} from "../../service/film-session.service";
import {FilmSession} from "../../model/FilmSession";
import {ActivatedRoute} from "@angular/router";
import {TicketService} from "../../service/ticket.service";
import {Ticket} from "../../model/Ticket";
import {TokenStorageService} from "../../service/token-storage.service";
import {ReservedDTO} from "../../model/dto/ReservedDTO";
import {ApiResponse} from "../../model/ApiResponse";

@Component({
  selector: 'app-film-session-details',
  templateUrl: './film-session-details.component.html',
  styleUrls: ['./film-session-details.component.css']
})
export class FilmSessionDetailsComponent implements OnInit {

  private filmSession: FilmSession;
  private tickets: Ticket[];
  private ticketDTO: ReservedDTO;
  private response: ApiResponse;

  constructor(
    private filmSessionService:FilmSessionService,
    private ticketService: TicketService,
    private rout: ActivatedRoute,
    private tokenStorage: TokenStorageService
  ) {
    this.ticketDTO = new ReservedDTO();
  }

  ngOnInit() {
    let sessionId: number = this.rout.snapshot.params['id'];
    this.filmSessionService.getOne(sessionId).subscribe(data => {
      this.filmSession = data;
      this.loadTickets(this.filmSession.id);
    })
  }

  loadTickets(sessionId: number) {
    this.ticketService.getAllUnreservedTicket(sessionId, 0, 10).subscribe(data => {
      this.tickets = data.content;
    })
  }

  reserved() {
    this.ticketDTO.userId = this.tokenStorage.getId();
    this.ticketService.reserved(this.ticketDTO).subscribe(data => {
      this.ticketDTO.ticketsId.length = 0;
      this.response = data;
    })
  }

  addToOrder(ticketId: number) {
    this.ticketDTO.ticketsId.push(ticketId);
  }
}
