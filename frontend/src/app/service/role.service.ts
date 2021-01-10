import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private url: string = `http://localhost:8090/api/roles`;

  constructor(private http: HttpClient) { }

  public getAll(): Observable<string[]> {
    return this.http.get<string[]>(`${this.url}`);
  }
}