import { Component, OnInit } from '@angular/core';
import {FilmService} from "../../../service/film.service";
import {TokenStorageService} from "../../../service/token-storage.service";
import {Film} from "../../../model/Film";
import {SortType} from "../../../model/SortType";
import {CinemaStatusService} from "../../../service/cinema-status.service";
import {ApiResponse} from "../../../model/ApiResponse";

@Component({
  selector: 'app-owner-films',
  templateUrl: './owner-films.component.html',
  styleUrls: ['./owner-films.component.css']
})
export class OwnerFilmsComponent implements OnInit {

  private films: Film[];
  private currentPage: number = 0;
  private currentSize: number = 10;
  private hasNext: boolean;
  private hasPrevious: boolean;
  private ownerId: number;
  private pageableParams: Map<string, string>;
  private statuses: string[] = [];
  private statusName: string;
  private response: ApiResponse;

  private sizes: number[] = [5, 10, 15, 20];

  constructor(
    private filmService: FilmService,
    private tokenStorage: TokenStorageService,
    private statusService: CinemaStatusService
  ) {
    this.pageableParams = new Map<string, string>();
  }

  ngOnInit() {
    this.pageableParams.set('page', String(this.currentPage));
    this.pageableParams.set('size', String(this.currentSize));
    this.ownerId = this.tokenStorage.getId();
    this.loadFilms(this.pageableParams);
    this.loadStatuses();
  }

  loadFilms(pageableParams: Map<string, string>) {
    this.filmService.getAllOwnerFilms(this.ownerId, pageableParams).subscribe(data => {
      this.films = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasNext = data.last;
      this.hasPrevious = data.first;
    })
  }

  sort(fieldName: string) {
    let sortType: string = SortType.ASC;
    if (this.pageableParams.has(fieldName)) {
      if (this.pageableParams.get(fieldName) === sortType) {
        sortType = SortType.DESC;
      } else {
        sortType = SortType.ASC;
      }
    }
    this.pageableParams.set(fieldName, sortType);
    this.loadFilms(this.pageableParams);
  }

  changePage(page: number) {
    this.pageableParams.set('page', String(page));
    this.loadFilms(this.pageableParams);
  }

  changeSize() {
    this.pageableParams.set('size', String(this.currentSize));
    this.loadFilms(this.pageableParams);
  }

  changeStatus(id: number) {
    this.filmService.changeStatus(id, this.statusName).subscribe(data => {
      this.response = data;
    })
  }

  loadStatuses() {
    this.statusService.getAll().subscribe(data => {
      this.statuses = data;
    })
  }
}
