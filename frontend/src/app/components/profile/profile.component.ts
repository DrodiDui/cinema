import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {TicketService} from "../../service/ticket.service";
import {User} from "../../model/User";
import {Ticket} from "../../model/Ticket";
import {UpdateUserDTO} from "../../model/dto/UpdateUserDTO";
import {ApiResponse} from "../../model/ApiResponse";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private user: User;
  private tickets: Ticket[];
  private userDTO: UpdateUserDTO;
  private userId: number;
  private response: ApiResponse;
  private isEdit: boolean = false;
  private currentPage: number = 0;
  private hasPrevious: boolean = false;
  private hasNext: boolean = true;
  private unreservedTicket: number[] = [];

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private ticketService: TicketService,
  ) {
    this.response = new ApiResponse();
  }

  ngOnInit() {
    let userEmail: string = this.tokenStorage.getEmail();
    this.userId = this.tokenStorage.getId();
    this.userService.getOne(userEmail).subscribe(data => {
      this.user = data;
      this.userDTO = new UpdateUserDTO(this.user);
      this.getTickets(this.currentPage);
    })
  }

  getTickets(page: number) {
    this.ticketService.getAllUserTicket(this.user.id, page, 5).subscribe(data => {
      this.tickets = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasPrevious = data.first;
      this.hasNext = data.last;
    })
  }

  editProfile() {
    this.userService.updateUser(this.userId, this.userDTO).subscribe(data => {
      this.response = data;
      this.isEdit = false;
    });
  }

  unreserved(ticketId: number) {
    this.ticketService.unreservedOne(ticketId).subscribe(data => {
      this.response = data;
    })
  }

  unreservedAllTickets() {
    this.ticketService.unreservedAll(this.unreservedTicket).subscribe(data => {
      this.response = data;
      this.unreservedTicket.length = 0;
    })
  }

  addToUnreserved(ticketId: number) {
    this.unreservedTicket.push(ticketId);
  }
}
