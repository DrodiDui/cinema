import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/User";
import {CreateUserDTO} from "../model/dto/CreateUserDTO";
import {ApiResponse} from "../model/ApiResponse";
import {Page} from "../model/Page";
import {UpdateUserDTO} from "../model/dto/UpdateUserDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `http://localhost:8091/api/users`;

  constructor(private http: HttpClient) {
  }

  public getAll(page: number, size: number): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}?page=${page}&size=${size}`);
  }

  public getOne(email: string): Observable<User> {
    return this.http.get<User>(`${this.url}/${email}`);
  }

  public create(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, userDTO);
  }

  public registration(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/registration`, userDTO);
  }

  public changeRole(userId: number, roleName: string): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${userId}/role?role=${roleName}`, null);
  }

  public changeStatus(userId: number, statusName: string): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${userId}/status?status=${statusName}`, null);
  }

  public updateUser(userId: number, userDTO: UpdateUserDTO): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${userId}`, userDTO);
  }
}
