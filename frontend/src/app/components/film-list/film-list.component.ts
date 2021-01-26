import { Component, OnInit } from '@angular/core';
import {FilmService} from "../../service/film.service";
import {Film} from "../../model/Film";

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent implements OnInit {

  private films: Film[];
  private currentPage: number = 0;
  private hasNext: boolean;
  private hasPrevious: boolean;
  private filmName: string;
  private sortParams: Map<string, string>;

  constructor(
    private filmService: FilmService
  ) {
    this.sortParams = new Map<string, string>();
  }

  ngOnInit() {
    this.loadFilms();
  }

  loadFilms() {
    if (this.filmName && this.filmName !== null) {
      this.loadAllFilmsByName();
    } else {
      this.loadAllFilms(this.currentPage);
    }
  }

  loadAllFilmsByName() {
    this.filmService.getAllFilmsByName(this.filmName).subscribe(data => {
      this.films = data;
    })
  }

  loadAllFilms(page: number) {
    this.filmService.getAllFilms(page, 10).subscribe(data => {
      this.films = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

}
