import {Component, OnInit} from '@angular/core';
import {FilmStatistics} from "../../../model/FilmStatistics";
import {FilmStatisticsService} from "../../../service/film-statistics.service";
import {TokenStorageService} from "../../../service/token-storage.service";
import {log} from "util";
import {SortType} from "../../../model/SortType";

@Component({
  selector: 'app-film-statistics',
  templateUrl: './film-statistics.component.html',
  styleUrls: ['./film-statistics.component.css']
})
export class FilmStatisticsComponent implements OnInit {

  private filmsStatistics: FilmStatistics[] = [];
  private ownerId: number;
  private pageableParams: Map<string, string>;
  private currentPage: number = 0;
  private currentSize: number = 10;
  private hasNext: boolean;
  private hasPrevious: boolean;

  constructor(
    private filmStatisticsService: FilmStatisticsService,
    private tokenStorage: TokenStorageService
  ) {
    this.ownerId = this.tokenStorage.getId();
    this.pageableParams = new Map<string, string>();
  }

  ngOnInit() {
    this.pageableParams.set('page', String(this.currentPage));
    this.pageableParams.set('size', String(this.currentSize));
    this.loadReservedTicketsCount(this.pageableParams);
  }

  loadReservedTicketsCount(pageableParam?: Map<string, string>) {
    this.filmStatisticsService.getReservedTicketsCount(this.ownerId, pageableParam).subscribe(data => {
        this.filmsStatistics = data.content;
        this.currentPage = data.pageable.pageNumber;
        this.hasNext = data.last;
        this.hasPrevious = data.first;
      }, error => {
        console.log("error");
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
    this.loadReservedTicketsCount(this.pageableParams);
  }

  changePage(page: number) {
    this.pageableParams.set('page', String(page));
    this.loadReservedTicketsCount(this.pageableParams);
  }

}
