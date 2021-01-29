import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Cinema} from "../model/Cinema";
import {ApiResponse} from "../model/ApiResponse";
import {CreateCinemaDTO} from "../model/dto/cinema/CreateCinemaDTO";
import {UpdateCinemaDTO} from "../model/dto/cinema/UpdateCinemaDTO";
import {Page} from "../model/Page";

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private url: string = `http://localhost:8091/api/cinemas`;

  constructor(private http: HttpClient) { }

  public getAll(pageableParams: Map<string, string>): Observable<Page<Cinema>> {
    let params: string = "";
    pageableParams.forEach(((value, key) => {
      params += key.toString() + "=" + value.toString() + "&";
    }))
    return this.http.get<Page<Cinema>>(`${this.url}?${params}`);
  }

  public getAllUserCinemas(ownerId: number, pageableParams: Map<string, string>): Observable<Page<Cinema>> {
    let params: string = "";
    pageableParams.forEach(((value, key) => {
      params += key.toString() + "=" + value.toString() + "&";
    }))
    return this.http.get<Page<Cinema>>(`${this.url}/all/${ownerId}?${params}`);
  }

  public getByManagerId(managerId: number): Observable<Cinema> {
    return this.http.get<Cinema>(`${this.url}/manager/${managerId}`);
  }

  public getByName(cinemaName: string): Observable<Cinema> {
    return this.http.get<Cinema>(`${this.url}/${cinemaName}`);
  }

  public create(cinemaDTO: CreateCinemaDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, cinemaDTO);
  }

  public update(cinemaDTO: UpdateCinemaDTO): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}`, cinemaDTO);
  }

  public changeStatus(cinemaId: number, statusName: string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}/${cinemaId}?status=${statusName}`, null);
  }

}
