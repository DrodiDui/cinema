import {Hall} from "../../Hall";

export class UpdateHallDTO {

  id?: number;
  hallName?: string;
  floor?: number;
  rowsNumbers?: number;
  numberSeatsPerRow?: number;
  status: string;

  public constructor(hall: Hall) {
    this.id = hall.id;
    this.hallName = hall.hallName;
    this.floor = hall.floor;
    this.rowsNumbers = hall.rowsNumbers;
    this.numberSeatsPerRow = hall.numberSeatsPerRow;
    this.status = hall.status;
  }

}
