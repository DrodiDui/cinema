import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/User";
import {CreateUserDTO} from "../model/dto/CreateUserDTO";
import {ApiResponse} from "../model/ApiResponse";
import {Page} from "../model/Page";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = `http://localhost:8090/api/users`;

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Page<User>> {
    return this.http.get<Page<User>>(`${this.url}`);
  }

  public getOne(id: number): Observable<User> {
    return this.http.get<User>(`${this.url}/${id}`);
  }

  public create(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, userDTO);
  }

  public registration(userDTO: CreateUserDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/registration`, userDTO);
  }

  public changeRole(userId: number, roleName: string):Observable<ApiResponse> {
    // @ts-ignore
    return this.http.put<ApiResponse>(`${this.url}/${userId}/role?role=${roleName}`);
  }

  public changeStatus(userId: number, statusName: string):Observable<ApiResponse> {
    // @ts-ignore
    return this.http.put<ApiResponse>(`${this.url}/${userId}/status?status=${statusName}`);
  }

}
