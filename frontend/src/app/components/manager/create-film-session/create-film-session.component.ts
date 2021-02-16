import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ApiResponse} from 'src/app/model/ApiResponse';
import {CreateFilmSessionDTO} from 'src/app/model/dto/film-session/CreaetFilmSessionDTO';
import {CinemaStatusService} from 'src/app/service/cinema-status.service';
import {FilmSessionService} from 'src/app/service/film-session.service';
import {TokenStorageService} from "../../../service/token-storage.service";
import {Film} from "../../../model/Film";
import {FilmService} from "../../../service/film.service";
import {CinemaService} from "../../../service/cinema.service";
import {Cinema} from "../../../model/Cinema";
import {ApiErrorResponse} from "../../../model/ApiErrorResponse";

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
  private readonly managerId: number;
  private cinema: Cinema;
  private errorResponse: ApiErrorResponse;

  constructor(
    private filmSessionService: FilmSessionService,
    private filmSessionStatus: CinemaStatusService,
    private cinemaService: CinemaService,
    private tokenStorage: TokenStorageService,
    private filmService: FilmService,
    private rout: ActivatedRoute
  ) {
    this.filmSessionDTO = new CreateFilmSessionDTO;
    this.response = new ApiResponse;
    this.managerId = this.tokenStorage.getId();
  }

  ngOnInit() {
    this.filmSessionDTO.hallId = this.rout.snapshot.params['hall-id'];
    this.filmSessionDTO.managerId = this.managerId;
    this.loadFilmSessionStatuses();
    this.loadCinema();
  }

  loadFilmSessionStatuses() {
    this.filmSessionStatus.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  createFilmSession() {
    this.filmSessionDTO.showTime = this.filmSessionDTO.showTime.replace("T", " ");
    this.filmSessionService.create(this.filmSessionDTO).subscribe(data => {
      this.response = data;
    }, error => {
      this.errorResponse = error.error;
    })
  }

  loadFilms(ownerId: number) {
    this.filmService.getAllOwnerActiveFilms(ownerId).subscribe(data => {
      this.films = data;
    }, error => {
      this.errorResponse = error;
      console.log(this.errorResponse);
    });
  }

  loadCinema() {
    this.cinemaService.getByManagerId(this.managerId).subscribe(data => {
      this.cinema = data;
      this.loadFilms(this.cinema.ownerId);
    })
  }


}
