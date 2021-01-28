import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../service/cinema.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UpdateCinemaDTO} from "../../model/dto/UpdateCinemaDTO";
import {Cinema} from "../../model/Cinema";
import {CinemaStatusService} from "../../service/cinema-status.service";
import {ApiResponse} from "../../model/ApiResponse";

@Component({
  selector: 'app-update-cinema',
  templateUrl: './update-cinema.component.html',
  styleUrls: ['./update-cinema.component.css']
})
export class UpdateCinemaComponent implements OnInit {

  private cinemaDTO: UpdateCinemaDTO;
  private cinema: Cinema;
  private statuses: string[] = [];
  private response: ApiResponse;

  constructor(
    private cinemaService: CinemaService,
    private statusService: CinemaStatusService,
    private rout: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    let cinemaName: string = this.rout.snapshot.params['name'];
    this.loadCinema(cinemaName);
    this.loadStatuses();
  }

  private loadCinema(cinemaName: string) {
    this.cinemaService.getByName(cinemaName).subscribe(data => {
      this.cinema = data;
      this.cinemaDTO = new UpdateCinemaDTO(this.cinema);
    })
  }

  loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  updateCinema() {
    this.cinemaService.update(this.cinemaDTO).subscribe(data => {
      this.response = data;
      this.router.navigate(['/owner/cinemas']);
    });
  }

}
