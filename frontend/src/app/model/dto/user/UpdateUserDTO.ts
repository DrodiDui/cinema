import {User} from "../../User";

export class UpdateUserDTO {

  firstName?: string;
  lastName?: string;

  constructor(user: User) {
    this.firstName = user.firstName;
    this.lastName = user.lastName;
  }
}
