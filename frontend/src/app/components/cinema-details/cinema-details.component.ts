import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../service/cinema.service";
import {ActivatedRoute} from "@angular/router";
import {Cinema} from "../../model/Cinema";
import {HallService} from "../../service/hall.service";
import {Hall} from "../../model/Hall";

@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.css']
})
export class CinemaDetailsComponent implements OnInit {

  private cinema: Cinema;
  private halls: Hall[];

  constructor(
    private cinemaService: CinemaService,
    private hallService: HallService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    let cinemaName: string = this.route.snapshot.params['name'];
    this.cinemaService.getByName(cinemaName).subscribe(data => {
      this.cinema = data;
      this.loadHalls(this.cinema.id);
    })
  }

  private loadHalls(cinemaId: number) {
    this.hallService.getAllCinemaHalls(cinemaId, 0, 10).subscribe(data => {
      this.halls = data.content;
    })
  }

}
