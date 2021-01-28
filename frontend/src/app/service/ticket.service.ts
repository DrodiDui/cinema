import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Ticket} from "../model/Ticket";
import {ReservedDTO} from "../model/dto/ticket/ReservedDTO";
import {ApiResponse} from "../model/ApiResponse";
import {Page} from "../model/Page";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private url: string = `http://localhost:8091/api/tickets`;

  constructor(private http: HttpClient) { }

  public getAllUnreservedTicket(sessionId: number, page: number, size: number): Observable<Page<Ticket>> {
    return this.http.get<Page<Ticket>>(`${this.url}/${sessionId}/all?page=${page}&size=${size}`);
  }

  public getAllUserTicket(userId: number, page: number, size: number): Observable<Page<Ticket>> {
    return this.http.get<Page<Ticket>>(`${this.url}/${userId}?page=${page}&size=${size}`);
  }

  public reserved(reservedDTO: ReservedDTO):Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/reserved`, reservedDTO);
  }

  public unreservedAll(ticketsId: number[]):Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/unreserved`, ticketsId);
  }

  public unreservedOne(ticketId: number):Observable<ApiResponse> {
    return this.http.put<ApiResponse>(`${this.url}/unreserved/${ticketId}`,null);
  }

}
