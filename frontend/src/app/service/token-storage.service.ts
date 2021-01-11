import { Injectable } from '@angular/core';
import {TokenResponse} from "../model/TokenResponse";

const TOKEN: string = "token";
const EMAIL: string = "email";
const ROLE: string = "role";
const ID: string = "id";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  saveTokenResponse(tokenResponse: TokenResponse) {
    sessionStorage.removeItem(TOKEN);
    sessionStorage.setItem(TOKEN, tokenResponse.token);
    sessionStorage.removeItem(EMAIL);
    sessionStorage.setItem(EMAIL, tokenResponse.email);
    sessionStorage.removeItem(ROLE);
    sessionStorage.setItem(ROLE, tokenResponse.role);
    sessionStorage.removeItem(ID);
    sessionStorage.setItem(ID, String(tokenResponse.id));
  }

  getToken(): string {
    return sessionStorage.getItem(TOKEN);
  }

  getEmail(): string {
    return sessionStorage.getItem(EMAIL);
  }

  getRole(): string {
    return sessionStorage.getItem(ROLE);
  }

  getId(): number {
    return Number(sessionStorage.getItem(ID));
  }

  logout() {
    sessionStorage.removeItem(TOKEN)
    sessionStorage.removeItem(EMAIL)
    sessionStorage.removeItem(ROLE)
    sessionStorage.removeItem(ID)
  }

}
