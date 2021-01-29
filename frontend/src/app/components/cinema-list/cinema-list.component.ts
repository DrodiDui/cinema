import {Component, OnInit} from '@angular/core';
import {CinemaService} from "../../service/cinema.service";
import {Cinema} from "../../model/Cinema";
import {SortType} from "../../model/SortType";

@Component({
  selector: 'app-cinema-list',
  templateUrl: './cinema-list.component.html',
  styleUrls: ['./cinema-list.component.css']
})
export class CinemaListComponent implements OnInit {

  private cinemas: Cinema[];
  private hasNext: boolean = true;
  private hasPrevious: boolean = true;
  private currentPage: number = 0;
  private pageableParams: Map<string, string>;

  constructor(
    private cinemaService: CinemaService
  ) {
    this.pageableParams = new Map<string, string>();
  }

  ngOnInit() {
    this.pageableParams.set("page", String(this.currentPage));
    this.pageableParams.set("size", String(10));
    this.loadCinemas(this.pageableParams);
  }

  loadCinemas(pageableParams?: Map<string, string>) {
    this.cinemaService.getAll(pageableParams).subscribe(data => {
      this.cinemas = data.content;
      this.currentPage = data.pageable.pageNumber;
      this.hasPrevious = data.first;
      this.hasNext = data.last;
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
    this.loadCinemas(this.pageableParams);
  }

  changePage(page: number) {
    this.pageableParams.set("page", String(page))
    this.loadCinemas(this.pageableParams);
  }

}
