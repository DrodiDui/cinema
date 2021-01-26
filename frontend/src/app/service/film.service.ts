import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Film} from "../model/Film";
import {Page} from "../model/Page";
import {CreateFilmDTO} from "../model/dto/CreateFilmDTO";
import {ApiResponse} from "../model/ApiResponse";

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  private url: string = `http://localhost:8091/api/films`

  constructor(
    private http: HttpClient
  ) { }

  getAllFilms(page: number, size: number, sortParams?: Map<string, string>): Observable<Page<Film>> {
    return this.http.get<Page<Film>>(`${this.url}?page=${page}&size=${size}&sortParams=${sortParams}`);
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
}