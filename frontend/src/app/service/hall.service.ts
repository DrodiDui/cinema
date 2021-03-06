import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Hall} from "../model/Hall";
import {Page} from "../model/Page";
import {CreateHallDTO} from "../model/dto/hall/CreateHallDTO";
import {ApiResponse} from "../model/ApiResponse";
import {UpdateHallDTO} from "../model/dto/hall/UpdateHallDTO";

@Injectable({
  providedIn: 'root'
})
export class HallService {

  private url: string = `http://localhost:8091/api/halls`

  constructor(private http: HttpClient) { }

  getAllCinemaHalls(cinemaId: number, page: number, size: number): Observable<Page<Hall>> {
    return this.http.get<Page<Hall>>(`${this.url}/${cinemaId}/all?page=${page}&size=${size}`);
  }

  getHall(cinemaName: string, hallName: string): Observable<Hall>{
    return this.http.get<Hall>(`${this.url}/${cinemaName}/${hallName}`);
  }

  createHall(hallDTO: CreateHallDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, hallDTO);
  }

  updateHall(hallDTO: UpdateHallDTO): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}`, hallDTO);
  }

}
