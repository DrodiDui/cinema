import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {TicketService} from "../../service/ticket.service";
import {User} from "../../model/User";
import {Ticket} from "../../model/Ticket";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private user: User;
  private tickets: Ticket[];

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private ticketService: TicketService
  ) { }

  ngOnInit() {
    let userEmail: string = this.tokenStorage.getEmail();
    this.userService.getOne(userEmail).subscribe(data => {
      this.user = data;
      this.getTickets(data.id);
    })
  }

  getTickets(userId: number) {
    this.ticketService.getAllUserTicket(userId, 0, 10).subscribe(data => {
      this.tickets = data.content;
    })
  }

}
