import {Pageable} from "./Pageable";

export class Page<T> {

  content: T[];
  totalPages: number;
  totalElements: number;
  pageable: Pageable;
  last: boolean;
  first: boolean;

}
