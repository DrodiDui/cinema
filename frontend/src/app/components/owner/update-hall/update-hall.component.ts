import { Component, OnInit } from '@angular/core';
import {HallService} from "../../../service/hall.service";
import {CinemaStatusService} from "../../../service/cinema-status.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Hall} from "../../../model/Hall";
import {UpdateHallDTO} from "../../../model/dto/hall/UpdateHallDTO";
import {ApiResponse} from "../../../model/ApiResponse";

@Component({
  selector: 'app-update-hall',
  templateUrl: './update-hall.component.html',
  styleUrls: ['./update-hall.component.css']
})
export class UpdateHallComponent implements OnInit {

  private hall: Hall;
  private hallDTO: UpdateHallDTO;
  private statuses: string[] = [];
  private response: ApiResponse;

  constructor(
    private hallService: HallService,
    private statusService: CinemaStatusService,
    private router: Router,
    private rout: ActivatedRoute
  ) { }

  ngOnInit() {
    let cinemaName: string = this.rout.snapshot.params['cinema-name'];
    let hallName: string = this.rout.snapshot.params['hall-name'];
    this.loadHall(cinemaName, hallName);
    this.loadStatuses();
  }

  private loadHall(cinemaName: string, hallName: string) {
    this.hallService.getHall(cinemaName, hallName).subscribe(data => {
      this.hall = data;
      this.hallDTO = new UpdateHallDTO(this.hall);
    })
  }

  private loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }

  private updateHall() {
    this.hallService.updateHall(this.hallDTO).subscribe(data => {
      this.response = data;
    })
    this.router.navigate([`owner/cinema/${this.hall.cinemaName}/halls`]);
  }

}
