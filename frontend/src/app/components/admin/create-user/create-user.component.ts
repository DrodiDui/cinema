import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../service/user.service";
import {RoleService} from "../../../service/role.service";
import {UserStatusService} from "../../../service/user-status.service";
import {CreateUserDTO} from "../../../model/dto/user/CreateUserDTO";
import {ApiResponse} from "../../../model/ApiResponse";
import {TokenStorageService} from "../../../service/token-storage.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  private roles: string[] = [];
  private statuses: string[] = [];
  private userDTO: CreateUserDTO;
  private response: ApiResponse;

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private statusService: UserStatusService,
    private tokenStorage: TokenStorageService,
    private rout: ActivatedRoute
  ) {
    this.userDTO = new CreateUserDTO();
  }

  ngOnInit() {
    this.loadRoles();
    this.loadStatuses();

    if (this.isOwner()) {
      this.userDTO.cinemaId = this.rout.snapshot.params['id'];
    }

  }

  loadRoles() {
    this.roleService.getAll().subscribe(data => {
      this.roles = data;
    })
  }

  loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  createUser() {
    this.userService.create(this.userDTO).subscribe(data => {
      this.response = data;
    })
  }

  isOwner() {
    if (this.tokenStorage.getRole() === "ROLE_OWNER") {
      return true;
    }
    return false;
  }

}
