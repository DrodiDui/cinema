import { Component, OnInit } from '@angular/core';
import {CinemaService} from "../../../service/cinema.service";
import {TokenStorageService} from "../../../service/token-storage.service";
import {Cinema} from "../../../model/Cinema";
import {SortType} from "../../../model/SortType";

@Component({
  selector: 'app-user-cinema-list',
  templateUrl: './user-cinema-list.component.html',
  styleUrls: ['./user-cinema-list.component.css']
})
export class UserCinemaListComponent implements OnInit {

  private cinemas: Cinema[];
  private userId: number;
  private pageableParams: Map<string, string>;
  private hasNext: boolean = true;
  private hasPrevious: boolean = true;
  private currentPage: number = 0;
  private currentSize: number = 10;

  private sizes: number[] = [5, 10, 15];

  constructor(
    private cinemaService: CinemaService,
    private tokenStorage:TokenStorageService
  ) {
    this.userId = tokenStorage.getId();
    this.pageableParams = new Map<string, string>();
  }

  ngOnInit() {
    this.pageableParams.set("page", String(this.currentPage));
    this.pageableParams.set("size", String(this.currentSize));
    this.loadCinemas(this.pageableParams);
  }

  private loadCinemas(pageableParams?: Map<string, string>) {
    this.cinemaService.getAllUserCinemas(this.userId, pageableParams).subscribe(data => {
      this.cinemas = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasPrevious = data.first;
      this.hasNext = data.last;
    })
  }

  changePage(page: number) {
    this.pageableParams.set("page", String(page));
    this.loadCinemas(this.pageableParams);
  }

  sort(fieldName: string) {
    let sortType: string = SortType.ASC;
    if (this.pageableParams.has(fieldName) && (this.pageableParams.get(fieldName) === sortType)) {
      sortType = SortType.DESC;
    } else {
      sortType = SortType.ASC;
    }
    this.pageableParams.set(fieldName, sortType);
    this.loadCinemas(this.pageableParams);
  }

  changeSize() {
    this.pageableParams.set('size', String(this.currentSize));
    this.loadCinemas(this.pageableParams);
  }

}
