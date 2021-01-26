import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../../service/cinema.service";
import {TokenStorageService} from "../../../service/token-storage.service";
import {Cinema} from "../../../model/Cinema";

@Component({
  selector: 'app-user-cinema-list',
  templateUrl: './user-cinema-list.component.html',
  styleUrls: ['./user-cinema-list.component.css']
})
export class UserCinemaListComponent implements OnInit {

  private cinemas: Cinema[];
  private userId: number;

  constructor(
    private cinemaService: CinemaService,
    private tokenStorage:TokenStorageService
  ) {
    this.userId = tokenStorage.getId();
  }

  ngOnInit() {
    this.cinemaService.getAllUserCinemas(this.userId, 0, 10).subscribe(data => {
      this.cinemas = data.content;
    })
  }

}
