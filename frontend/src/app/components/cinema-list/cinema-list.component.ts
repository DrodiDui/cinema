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
  private hasNext: boolean = true;
  private hasPrevious: boolean = false;
  private currentPage: number = 0;

  constructor(
    private cinemaService: CinemaService
  ) { }

  ngOnInit() {
    this.loadCinemas(this.currentPage);
  }

  loadCinemas(page: number) {
    this.cinemaService.getAll(page, 10).subscribe(data => {
      this.cinemas = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasPrevious = data.first;
      this.hasNext = data.last;
    })
  }

}
