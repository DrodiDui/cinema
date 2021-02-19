import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {TicketService} from "../../service/ticket.service";
import {User} from "../../model/User";
import {Ticket} from "../../model/Ticket";
import {UpdateUserDTO} from "../../model/dto/user/UpdateUserDTO";
import {ApiResponse} from "../../model/ApiResponse";

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
  private currentSize: number = 10;
  private hasPrevious: boolean = false;
  private hasNext: boolean = true;
  private unreservedTicket: number[] = [];
  private sizes: number[] = [5, 10, 15, 20];

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private ticketService: TicketService,
  ) {
    this.response = new ApiResponse();
  }

  ngOnInit() {
    this.userId = this.tokenStorage.getId();
    this.loadUser();
    this.getTickets(this.currentPage);
  }

  loadUser() {
    let userEmail: string = this.tokenStorage.getEmail();
    this.userService.getOne(userEmail).subscribe(data => {
      this.user = data;
      this.userDTO = new UpdateUserDTO(this.user);
    })
  }

  getTickets(page: number) {
    this.ticketService.getAllUserTicket(this.user.id, page, this.currentSize).subscribe(data => {
      this.tickets = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasPrevious = data.first;
      this.hasNext = data.last;
    })
  }

  changeSize() {
    this.getTickets(this.currentPage);
  }

  editProfile() {
    this.userService.updateUser(this.userId, this.userDTO).subscribe(data => {
      this.response = data;
      this.isEdit = false;
      this.ngOnInit();
    });
  }

  unreserved(ticketId: number) {
    this.ticketService.unreservedOne(ticketId).subscribe(data => {
      this.response = data;
      this.getTickets(this.currentPage);
    })
  }

  unreservedAllTickets() {
    this.ticketService.unreservedAll(this.unreservedTicket).subscribe(data => {
      this.response = data;
      this.unreservedTicket.length = 0;
      this.getTickets(this.currentPage);
    })
  }

  addToUnreserved(ticketId: number) {
    this.unreservedTicket.push(ticketId);
  }
}
