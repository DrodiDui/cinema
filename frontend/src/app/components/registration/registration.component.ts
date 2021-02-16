import {Component} from '@angular/core';
import {UserService} from "../../service/user.service";
import {RoleService} from "../../service/role.service";
import {ApiResponse} from "../../model/ApiResponse";
import {SignUpDTO} from "../../model/dto/user/SignUpDTO";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {ApiErrorResponse} from "../../model/ApiErrorResponse";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  private signUpDTO: SignUpDTO;
  private apiResponse: ApiResponse;
  private errorResponse: ApiErrorResponse;

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private authService: AuthService,
    private router: Router
  ) {
    this.signUpDTO = new SignUpDTO();
  }

  save() {
    this.authService.signup(this.signUpDTO).subscribe(data => {
      this.apiResponse = data;
      this.router.navigate(['/login'])
    }, error => {
      this.errorResponse = error.error;
    });
  }

}
