import { Component, OnInit } from '@angular/core';
import {HallService} from "../../service/hall.service";
import {FilmSessionService} from "../../service/film-session.service";
import {ActivatedRoute} from "@angular/router";
import {FilmSession} from "../../model/FilmSession";
import {Hall} from "../../model/Hall";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-hall-details',
  templateUrl: './hall-details.component.html',
  styleUrls: ['./hall-details.component.css']
})
export class HallDetailsComponent implements OnInit {

  private hall: Hall;
  private filmSessions: FilmSession[];

  constructor(
    private hallService: HallService,
    private filmSessionService: FilmSessionService,
    private rout: ActivatedRoute,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
    let cinemaName: string = this.rout.snapshot.params['cinema-name'];
    let hallName: string = this.rout.snapshot.params['hall-name'];
    this.hallService.getHall(cinemaName, hallName).subscribe(data => {
      this.hall = data;
      this.loadFilmSessions(this.hall.id);
    })
  }

  private loadFilmSessions(hallId: number) {
    this.filmSessionService.getAll(hallId, 0, 10).subscribe(data => {
      this.filmSessions = data.content;
    })
  }

  isManager() {
    if (this.tokenStorage.getRole() === 'ROLE_MANAGER') {
      return true;
    }
    return false;
  }

}
