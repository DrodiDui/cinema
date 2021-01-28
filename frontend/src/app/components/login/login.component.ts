import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {LoginDTO} from "../../model/dto/user/LoginDTO";
import {TokenResponse} from "../../model/TokenResponse";
import {TokenStorageService} from "../../service/token-storage.service";
import {Router, RouterModule} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private loginDTO: LoginDTO;
  private tokenResponse: TokenResponse;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private router: Router
  ) {
    this.loginDTO = new LoginDTO();
    this.tokenResponse = new TokenResponse();
  }

  login() {
    this.authService.login(this.loginDTO).subscribe(data => {
      this.tokenResponse = data;
      this.tokenStorage.saveTokenResponse(this.tokenResponse);
      this.router.navigate(['']);
    })
  }

}
