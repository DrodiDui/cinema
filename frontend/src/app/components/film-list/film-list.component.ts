import { Component, OnInit } from '@angular/core';
import {FilmService} from "../../service/film.service";
import {Film} from "../../model/Film";
import {SortType} from "../../model/SortType";

@Component({
  selector: 'app-film-list',
  templateUrl: './film-list.component.html',
  styleUrls: ['./film-list.component.css']
})
export class FilmListComponent implements OnInit {

  private films: Film[];
  private currentPage: number = 0;
  private currentSize: number = 10;
  private hasNext: boolean;
  private hasPrevious: boolean;
  private filmName: string;
  private pageableParams: Map<string, string>;

  constructor(
    private filmService: FilmService
  ) {
    this.pageableParams = new Map<string, string>();
  }

  ngOnInit() {
    this.pageableParams.set("page", String(this.currentPage));
    this.pageableParams.set("size", String(this.currentSize));
    this.loadFilms(this.pageableParams);
  }

  loadFilms(params?: Map<string, string>) {
    if (this.filmName && this.filmName !== null) {
      this.loadAllFilmsByName();
    } else {
      this.loadAllFilms(this.pageableParams);
    }
  }

  loadAllFilmsByName() {
    this.filmService.getAllFilmsByName(this.filmName).subscribe(data => {
      this.films = data;
    })
  }

  loadAllFilms(pageableParams: Map<string, string>) {
    this.filmService.getAllFilms(pageableParams).subscribe(data => {
      this.films = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

  sort(fieldName: string) {
    let sortType: string = SortType.ASC;
    if (this.pageableParams.has(fieldName) && (this.pageableParams.get(fieldName) === sortType)) {
      sortType = SortType.DESC;
    } else {
      sortType = SortType.ASC;
    }
    this.pageableParams.set(fieldName, sortType);
    this.loadFilms(this.pageableParams);
  }

  changePage(page: number) {
    this.pageableParams.set('page', String(page));
    this.loadAllFilms(this.pageableParams);
  }


}
