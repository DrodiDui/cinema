import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../../service/cinema.service";
import {CinemaStatusService} from "../../../service/cinema-status.service";
import {CreateCinemaDTO} from "../../../model/dto/CreateCinemaDTO";
import {ApiResponse} from "../../../model/ApiResponse";
import {TokenStorageService} from "../../../service/token-storage.service";

@Component({
  selector: 'app-create-cinema',
  templateUrl: './create-cinema.component.html',
  styleUrls: ['./create-cinema.component.css']
})
export class CreateCinemaComponent implements OnInit {

  private cinemaStatuses: string[] = [];
  private cinemaDTO: CreateCinemaDTO;
  private response: ApiResponse;

  constructor(
    private cinemaService: CinemaService,
    private cinemaStatusService: CinemaStatusService,
    private tokenStorage: TokenStorageService
  ) {
    this.cinemaDTO = new CreateCinemaDTO();
  }

  ngOnInit() {
    this.loadCinemaStatuses();
  }

  loadCinemaStatuses() {
    this.cinemaStatusService.getAll().subscribe(data => {
      this.cinemaStatuses = data;
    })
  }

  createCinema() {
    this.cinemaDTO.ownerId = this.tokenStorage.getId();
    this.cinemaService.create(this.cinemaDTO).subscribe(data => {
      this.response = data;
    })
  }

}
