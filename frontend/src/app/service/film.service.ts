import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Film} from "../model/Film";
import {Page} from "../model/Page";
import {CreateFilmDTO} from "../model/dto/film/CreateFilmDTO";
import {ApiResponse} from "../model/ApiResponse";

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  private url: string = `http://localhost:8091/api/films`

  constructor(
    private http: HttpClient
  ) { }

  getAllFilms(pageableParams: Map<string, string>): Observable<Page<Film>> {
    let params: string = "";
    pageableParams.forEach(((value, key) => {
      params += key + "=" + value + "&";
    }))
    return this.http.get<Page<Film>>(`${this.url}?${params}`);
  }

  getAllOwnerFilms(ownerId: number, pageableParams: Map<string, string>): Observable<Page<Film>> {
    let params: string = "";
    pageableParams.forEach(((value, key) => {
      params += key + "=" + value + "&";
    }))
    return this.http.get<Page<Film>>(`${this.url}/all/${ownerId}?${params}`);
  }

  getAllFilmsByName(filmName: string): Observable<Film[]> {
    return this.http.get<Film[]>(`${this.url}/${filmName}/all`);
  }

  create(filmDTO: CreateFilmDTO): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(`${this.url}`, filmDTO);
  }

  getById(filmId: number): Observable<Film> {
    return this.http.get<Film>(`${this.url}/${filmId}`);
  }

  changeStatus(id: number, statusName: string): Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${id}/status?status=${statusName}`, null);
  }
}
