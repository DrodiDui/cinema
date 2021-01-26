import { Component, OnInit } from '@angular/core';
import {CreateFilmDTO} from "../../../model/dto/CreateFilmDTO";
import {ApiResponse} from "../../../model/ApiResponse";
import {FilmService} from "../../../service/film.service";
import {CinemaStatusService} from "../../../service/cinema-status.service";
import {TokenStorageService} from "../../../service/token-storage.service";

@Component({
  selector: 'app-create-film',
  templateUrl: './create-film.component.html',
  styleUrls: ['./create-film.component.css']
})
export class CreateFilmComponent implements OnInit {

  private filmDTO: CreateFilmDTO;
  private statuses: string[] = [];
  private response: ApiResponse;

  constructor(
    private filmService: FilmService,
    private statusService: CinemaStatusService,
    private tokenStorage: TokenStorageService
  ) {
    this.filmDTO = new CreateFilmDTO();
    this.response = new ApiResponse();
  }

  ngOnInit() {
    this.loadStatuses();
    this.filmDTO.ownerId = this.tokenStorage.getId();
  }

  loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  createFilm() {
    this.filmService.create(this.filmDTO).subscribe(data => {
      this.response = data;
    })
  }

}
