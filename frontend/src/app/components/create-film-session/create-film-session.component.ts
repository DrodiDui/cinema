import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiResponse } from 'src/app/model/ApiResponse';
import { CreateFilmSessionDTO } from 'src/app/model/dto/film-session/CreaetFilmSessionDTO';
import { CinemaStatusService } from 'src/app/service/cinema-status.service';
import { FilmSessionService } from 'src/app/service/film-session.service';
import {TokenStorageService} from "../../service/token-storage.service";
import {Film} from "../../model/Film";
import {FilmService} from "../../service/film.service";

@Component({
  selector: 'app-create-film-session',
  templateUrl: './create-film-session.component.html',
  styleUrls: ['./create-film-session.component.css']
})
export class CreateFilmSessionComponent implements OnInit {

  private statuses: string[] = [];
  private filmSessionDTO: CreateFilmSessionDTO;
  private response: ApiResponse;
  private films: Film[];
  private film: Film;
  private filmName: string;
  filmId: number;

  constructor(
    private filmSessionService: FilmSessionService,
    private filmSessionStatus: CinemaStatusService,
    private tokenStorage: TokenStorageService,
    private filmService: FilmService,
    private rout: ActivatedRoute
  ) {
    this.filmSessionDTO = new CreateFilmSessionDTO;
    this.response = new ApiResponse;
  }

  ngOnInit() {
    this.filmSessionDTO.hallId = this.rout.snapshot.params['hall-id'];
    this.filmSessionDTO.managerId = this.tokenStorage.getId();
    this.loadFilmSessionStatuses();
  }

  loadFilmSessionStatuses() {
    this.filmSessionStatus.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  createFilmSession() {
    this.filmSessionService.create(this.filmSessionDTO).subscribe(data => {
      this.response = data;
    })
  }

  loadFilms() {
    this.filmService.getAllFilmsByName(this.filmName).subscribe(data => {
      this.films = data;
    });
  }

  loadFilm() {
    this.filmService.getById(this.filmId).subscribe(data => {
      this.film = data;
    })
  }
}
