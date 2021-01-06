import {Component} from '@angular/core';
import {UserService} from "../../service/user.service";
import {RoleService} from "../../service/role.service";
import {ApiResponse} from "../../model/ApiResponse";
import {SignUpDTO} from "../../model/dto/SignUpDTO";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  private signUpDTO: SignUpDTO;
  private apiResponse: ApiResponse;

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private authService: AuthService
  ) {
    this.signUpDTO = new SignUpDTO();
  }

  save() {
    this.authService.signup(this.signUpDTO).subscribe(data => {
      this.apiResponse = data;
    });
  }

}
