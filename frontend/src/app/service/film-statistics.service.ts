import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Page} from "../model/Page";
import {FilmStatistics} from "../model/FilmStatistics";

@Injectable({
  providedIn: 'root'
})
export class FilmStatisticsService {

  private url: string = `http://localhost:8091/api/statistics/films`

  constructor(private http: HttpClient) { }

  public getReservedTicketsCount(ownerId: number, pageableParams: Map<string, string>): Observable<Page<FilmStatistics>> {
    let params: string = "";
    pageableParams.forEach(((value, key) => {
      params += key.toString() + "=" + value.toString() + "&";
    }))
    return this.http.get<Page<FilmStatistics>>(`${this.url}/${ownerId}?${params}`);
  }

}
