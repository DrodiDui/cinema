import {Component, OnInit} from '@angular/core';
import {HallService} from "../../service/hall.service";
import {FilmSessionService} from "../../service/film-session.service";
import {ActivatedRoute} from "@angular/router";
import {FilmSession} from "../../model/FilmSession";
import {Hall} from "../../model/Hall";
import {TokenStorageService} from "../../service/token-storage.service";
import {CinemaStatusService} from "../../service/cinema-status.service";
import {ApiResponse} from "../../model/ApiResponse";

@Component({
  selector: 'app-hall-details',
  templateUrl: './hall-details.component.html',
  styleUrls: ['./hall-details.component.css']
})
export class HallDetailsComponent implements OnInit {

  private hall: Hall;
  private filmSessions: FilmSession[];
  private statuses: string[];
  private statusName: string;
  private response: ApiResponse;
  private hallId: number;

  constructor(
    private hallService: HallService,
    private filmSessionService: FilmSessionService,
    private statusService: CinemaStatusService,
    private rout: ActivatedRoute,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
    this.loadHall();
  }


  private loadFilmSessions(hallId: number) {
    if (this.isManager()) {
      this.loadAllFilmSessions(hallId);
      this.loadStatuses();
    } else {
      this.loadActiveFilmSessions(hallId);
    }
  }

  private loadHall() {
    let cinemaName: string = this.rout.snapshot.params['cinema-name'];
    let hallName: string = this.rout.snapshot.params['hall-name'];
    this.hallService.getHall(cinemaName, hallName).subscribe(data => {
      this.hall = data;
      this.loadFilmSessions(this.hall.id);
    })
  }

  private loadActiveFilmSessions(hallId: number) {
    this.filmSessionService.getAllActive(hallId, 0, 10).subscribe(data => {
      this.filmSessions = data.content;
    })
  }

  private loadAllFilmSessions(hallId: number) {
    this.filmSessionService.getAll(hallId, 0, 10).subscribe(data => {
      this.filmSessions = data.content;
    })
  }

  private loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  private changeStatus(id: number) {
    this.filmSessionService.changeStatus(id, this.statusName).subscribe(data => {
      this.response = data;
      this.ngOnInit();
    })
  }

  isManager() {
    if (this.tokenStorage.getRole() === 'ROLE_MANAGER') {
      return true;
    }
    return false;
  }

}
