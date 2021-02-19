import {Component, OnInit} from '@angular/core';
import {FilmSessionService} from "../../service/film-session.service";
import {FilmSession} from "../../model/FilmSession";
import {ActivatedRoute} from "@angular/router";
import {TicketService} from "../../service/ticket.service";
import {Ticket} from "../../model/Ticket";
import {TokenStorageService} from "../../service/token-storage.service";
import {ReservedDTO} from "../../model/dto/ticket/ReservedDTO";
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
  private currentPage: number = 0;
  private hasNext: boolean;
  private hasPrevious: boolean;
  private currentSize: number = 10;
  private sizes: number[] = [5, 10, 15, 20];

  constructor(
    private filmSessionService:FilmSessionService,
    private ticketService: TicketService,
    private rout: ActivatedRoute,
    private tokenStorage: TokenStorageService
  ) {
    this.ticketDTO = new ReservedDTO();
    this.currentPage = 0;
  }

  ngOnInit() {
    this.loadSession();
  }

  loadSession() {
    let sessionId: number = this.rout.snapshot.params['id'];
    this.filmSessionService.getOne(sessionId).subscribe(data => {
      this.filmSession = data;
      this.loadTickets(this.currentPage);
    })
  }

  loadTickets(page: number) {
    this.ticketService.getAllUnreservedTicket(this.filmSession.id, page, this.currentSize).subscribe(data => {
      this.tickets = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

  changeSize() {
    this.loadTickets(this.currentPage);
  }

  reserved() {
    this.ticketDTO.userId = this.tokenStorage.getId();
    this.ticketDTO.userEmail = this.tokenStorage.getEmail();
    this.ticketService.reserved(this.ticketDTO).subscribe(data => {
      this.ticketDTO.ticketsId.length = 0;
      this.response = data;
      this.ngOnInit();
    })
  }

  addToOrder(ticketId: number) {
    this.ticketDTO.ticketsId.push(ticketId);
  }
}
