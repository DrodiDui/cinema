import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../../model/User";
import {Pageable} from "../../model/Pageable";
import {last} from "rxjs/operators";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private users: User[];
  private pageable: Pageable;
  private currentPage: number = 0;
  private hasNext: boolean;
  private hasPrevious: boolean;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.loadUser(this.currentPage);
  }

  loadUser(page: number) {
    this.userService.getAll(page, 10).subscribe(data => {
      this.users = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

}
