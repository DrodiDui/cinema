import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../service/user.service";
import {User} from "../../../model/User";
import {Pageable} from "../../../model/Pageable";
import {last} from "rxjs/operators";
import {ApiResponse} from "../../../model/ApiResponse";
import {Page} from "../../../model/Page";
import {RoleService} from "../../../service/role.service";
import {UserStatusService} from "../../../service/user-status.service";
import {templateJitUrl} from "@angular/compiler";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  private users: User[];
  private currentPage: number = 0;
  private hasNext: boolean;
  private hasPrevious: boolean;
  private roleName: string;
  private statusName: string;
  private response: ApiResponse;

  private roles: string[] = [];
  private statuses: string[] = [];

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private statusService: UserStatusService
  ) { }

  ngOnInit() {
    this.loadUser(this.currentPage);
    this.loadRoles();
    this.loadStatuses();
  }

  private loadRoles() {
    this.roleService.getAll().subscribe(data => {
      this.roles = data;
    })
  }

  loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  loadUser(page: number) {
    this.userService.getAll(page, 10).subscribe(data => {
      this.users = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

  changeRole(userId: number) {
    this.userService.changeRole(userId, this.roleName).subscribe(data => {
      this.response = data;
    })
  }

  changeStatus(userId: number) {
    this.userService.changeStatus(userId, this.statusName).subscribe(data => {
      this.response = data;
    })
  }

}
