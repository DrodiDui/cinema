import {Cinema} from "../Cinema";

export class UpdateCinemaDTO {

  id?:number;
  cinemaName?: string;
  country?: string;
  city?: string;
  address?: string;
  description?: string;
  status?: string;

  constructor(cinema: Cinema) {
    this.id = cinema.id;
    this.cinemaName = cinema.cinemaName;
    this.country = cinema.country;
    this.city = cinema.city;
    this.address = cinema.address;
    this.description = cinema.description;
    this.status = cinema.status;
  }

}
