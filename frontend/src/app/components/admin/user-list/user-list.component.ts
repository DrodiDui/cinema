import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {User} from "../../../model/User";
import {ApiResponse} from "../../../model/ApiResponse";
import {RoleService} from "../../../service/role.service";
import {UserStatusService} from "../../../service/user-status.service";
import {SortType} from "../../../model/SortType";

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
  private pageableParams: Map<string, string>;

  private roles: string[] = [];
  private statuses: string[] = [];

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private statusService: UserStatusService
  ) {
    this.pageableParams = new Map<string, string>();
  }


  ngOnInit() {
    this.pageableParams.set("page", String(this.currentPage));
    this.pageableParams.set("size", String(10));
    this.loadUser(this.pageableParams);
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

  loadUser(pageableParams?: Map<string, string>) {
    this.userService.getAll(pageableParams).subscribe(data => {
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

  changePage(page: number) {
    this.pageableParams.set('page', String(page));
    this.loadUser(this.pageableParams);
  }

  sort(fieldName: string) {
    let sortType: string = SortType.ASC;
    if (this.pageableParams.has(fieldName) && (this.pageableParams.get(fieldName) === sortType)) {
      sortType = SortType.DESC;
    } else {
      sortType = SortType.ASC;
    }
    this.pageableParams.set(fieldName, sortType);
    this.loadUser(this.pageableParams);
  }

}
