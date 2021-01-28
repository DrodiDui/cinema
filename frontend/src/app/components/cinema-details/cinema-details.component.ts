import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../service/cinema.service";
import {ActivatedRoute} from "@angular/router";
import {Cinema} from "../../model/Cinema";
import {HallService} from "../../service/hall.service";
import {Hall} from "../../model/Hall";
import {TokenStorageService} from "../../service/token-storage.service";

@Component({
  selector: 'app-cinema-details',
  templateUrl: './cinema-details.component.html',
  styleUrls: ['./cinema-details.component.css']
})
export class CinemaDetailsComponent implements OnInit {

  private cinema: Cinema;
  private halls: Hall[];
  private cinemaName: string;

  constructor(
    private cinemaService: CinemaService,
    private hallService: HallService,
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService
  ) {
  }

  ngOnInit() {
    this.cinemaName = this.route.snapshot.params['cinema-name'];
    if (this.cinemaName &&  this.cinemaName !== null) {
      this.loadCinema(this.cinemaName);
    } else {
      this.loadManagerCinema()
    }
  }

  private loadCinema(cinemaName: string) {
    this.cinemaService.getByName(cinemaName).subscribe(data => {
      this.cinema = data;
      this.loadHalls(this.cinema.id);
    })
  }

  private loadManagerCinema() {
    let managerId: number = this.tokenStorage.getId();
    this.cinemaService.getByManagerId(managerId).subscribe(data => {
      let cinemaId: number = data.id;
      this.cinemaName = data.cinemaName;
      this.loadHalls(cinemaId);
    })
  }

  private loadHalls(cinemaId: number) {
    this.hallService.getAllCinemaHalls(cinemaId, 0, 10).subscribe(data => {
      this.halls = data.content;
    })
  }

  isOwner() {
    if (this.tokenStorage.getRole() === 'ROLE_OWNER') {
      return true;
    }
    return false;
  }

  isManager() {
    if (this.tokenStorage.getRole() === 'ROLE_MANAGER') {
      return true;
    }
    return false;
  }


}
