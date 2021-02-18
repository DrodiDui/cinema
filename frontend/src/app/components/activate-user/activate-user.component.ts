import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ApiResponse} from "../../model/ApiResponse";

@Component({
  selector: 'app-activate-user',
  templateUrl: './activate-user.component.html',
  styleUrls: ['./activate-user.component.css']
})
export class ActivateUserComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    let activationCode = this.activatedRoute.snapshot.params['code'];
    this.userService.activateUser(activationCode).subscribe(data => {
      this.router.navigate(['login']);
    }, error => {
      console.log(error);
    });
  }

}
