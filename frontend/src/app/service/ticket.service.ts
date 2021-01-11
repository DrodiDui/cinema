import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../model/Ticket";
import {ReservedDTO} from "../model/dto/ReservedDTO";
import {ApiResponse} from "../model/ApiResponse";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private url: string = `http://localhost:8091/api/tickets`;

  constructor(private http: HttpClient) { }

  public getAllUnreservedTicket(page: number, size: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.url}?page=${page}&size=${size}`);
  }

  public getAllUserTicket(userId: number, page: number, size: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.url}/${userId}?page=${page}&size=${size}`);
  }

  public reserved(reservedDTO: ReservedDTO):Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}`, reservedDTO);
  }

  public unreserved(ticketId: number):Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/${ticketId}`, null);
  }

}
