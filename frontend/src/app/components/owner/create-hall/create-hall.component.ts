import { Component, OnInit } from '@angular/core';
import {HallService} from "../../../service/hall.service";
import {CinemaStatusService} from "../../../service/cinema-status.service";
import {CreateHallDTO} from "../../../model/dto/CreateHallDTO";
import {ActivatedRoute} from "@angular/router";
import {ApiResponse} from "../../../model/ApiResponse";

@Component({
  selector: 'app-create-hall',
  templateUrl: './create-hall.component.html',
  styleUrls: ['./create-hall.component.css']
})
export class CreateHallComponent implements OnInit {

  private statuses: string[] = [];
  private hallDTO: CreateHallDTO;
  private response: ApiResponse;

  constructor(
    private hallService: HallService,
    private hallStatusService: CinemaStatusService,
    private rout: ActivatedRoute
  ) {
    this.hallDTO = new CreateHallDTO();
    this.response = new ApiResponse();
  }

  ngOnInit() {
    this.hallDTO.cinemaId = this.rout.snapshot.params['id'];
    this.hallStatusService.getAll().subscribe(data => {
      this.statuses = data;
    });
  }

  creatHall() {
    this.hallService.createHall(this.hallDTO).subscribe(data => {
      this.response = data;
    })
  }

}
