import {FilmSession} from "../../FilmSession";

export class UpdateFilmSessionDTO {

  filmName?: string;
  ticketCost?: number;
  hallId?: number;
  managerId?: number;
  status?: string;
  filmId?: number;
  showTime?: string;


  constructor(filmSession: FilmSession) {
    this.filmName = filmSession.filmName;
    this.ticketCost = filmSession.ticketCost;
    this.status = filmSession.status;
    this.showTime = filmSession.showTime;
  }
}
