import {Pageable} from "./Pageable";

export class Page<T> {

  content: T[];
  pageable: Pageable

}
