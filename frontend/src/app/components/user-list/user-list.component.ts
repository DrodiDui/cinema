import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../model/User";
import {Pageable} from "../../model/Pageable";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private users: User[];
  private pageable: Pageable;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data.content;
      this.pageable = data.pageable;
    })
  }

}
