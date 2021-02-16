import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../model/Page";
import {FilmSession} from "../model/FilmSession";
import { CreateFilmSessionDTO } from '../model/dto/film-session/CreaetFilmSessionDTO';
import { ApiResponse } from '../model/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class FilmSessionService {

  private url: string = `http://localhost:8091/api/film-sessions`;

  constructor(private http: HttpClient) { }

  public getAllActive(hallId: number, page: number, size: number): Observable<Page<FilmSession>> {
    return this.http.get<Page<FilmSession>>(`${this.url}/${hallId}/active?page=${page}&size=${size}`);
  }

  public getAll(hallId: number, page: number, size: number): Observable<Page<FilmSession>> {
    return this.http.get<Page<FilmSession>>(`${this.url}/${hallId}/all?page=${page}&size=${size}`);
  }

  public getOne(sessionId: number): Observable<FilmSession> {
    return this.http.get<FilmSession>(`${this.url}/${sessionId}`);
  }

  public create(filmSessionDTO: CreateFilmSessionDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, filmSessionDTO);
  }

  public changeStatus(filmSessionId: number, statusName: string): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${filmSessionId}/status?status=${statusName}`, null);
  }
}
