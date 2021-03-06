import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ApiResponse} from "../model/ApiResponse";
import {HttpClient} from "@angular/common/http";
import {SignUpDTO} from "../model/dto/user/SignUpDTO";
import {LoginDTO} from "../model/dto/user/LoginDTO";
import {TokenResponse} from "../model/TokenResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string = `http://localhost:8091/api/auth`;

  constructor(private http: HttpClient) { }

  public signup(signUpDTO: SignUpDTO):Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/sign-up`, signUpDTO);
  }

  public login(loginDTO: LoginDTO): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.url}/sign-in`, loginDTO);
  }

}
