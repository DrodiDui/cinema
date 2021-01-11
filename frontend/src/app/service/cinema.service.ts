import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Cinema} from "../model/Cinema";
import {ApiResponse} from "../model/ApiResponse";
import {CreateCinemaDTO} from "../model/dto/CreateCinemaDTO";
import {UpdateCinemaDTO} from "../model/dto/UpdateCinemaDTO";
import {Page} from "../model/Page";

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private url: string = `http://localhost:8091/api/cinemas`;

  constructor(private http: HttpClient) { }

  public getAll(page: number, size: number): Observable<Page<Cinema>> {
    return this.http.get<Page<Cinema>>(`${this.url}?page=${page}&size=${size}`);
  }

  public getAllUserCinemas(userId: number, page: number, size: number): Observable<Page<Cinema>> {
    return this.http.get<Page<Cinema>>(`${this.url}/${userId}/all?page=${page}&size=${size}`);
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
