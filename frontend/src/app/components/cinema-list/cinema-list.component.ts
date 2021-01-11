import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../service/cinema.service";
import {Cinema} from "../../model/Cinema";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit {

  private cinemas: Cinema[];

  constructor(
    private cinemaService: CinemaService
  ) { }

  ngOnInit() {
    this.cinemaService.getAll(0, 10).subscribe(data => {
      this.cinemas = data.content;
    })
  }

}
