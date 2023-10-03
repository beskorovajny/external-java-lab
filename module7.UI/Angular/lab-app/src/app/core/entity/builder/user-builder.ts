import {UserRole} from "../../enum/user-role";
import {Receipt} from "../receipt";
import {Jwt} from "../jwt/jwt";
import {User} from "../user";

export class UserBuilder {
  private _id: number = 0;
  private _firstName: string = '';
  private _lastName: string = '';
  private _email: string = '';
  private _role: UserRole = UserRole.GUEST;
  private _jwt: Jwt = new Jwt('', UserRole.GUEST, '', '');
  private _receipts: Set<Receipt> = new Set<Receipt>();

  setId(id: number): UserBuilder {
    this._id = id;
    return this;
  }

  setFirstName(firstName: string): UserBuilder {
    this._firstName = firstName;
    return this;
  }

  setLastName(lastName: string): UserBuilder {
    this._lastName = lastName;
    return this;
  }

  setEmail(email: string): UserBuilder {
    this._email = email;
    return this;
  }

  setRole(role: UserRole): UserBuilder {
    this._role = role;
    return this;
  }

  setJwt(jwt: Jwt): UserBuilder {
    this._jwt = jwt;
    return this;
  }

  setReceipts(receipts: Set<Receipt>): UserBuilder {
    this._receipts = receipts;
    return this;
  }

  addReceipt(receipt: Receipt): UserBuilder {
    this._receipts.add(receipt);
    return this;
  }

  build(): User {
    return new User(this._id, this._firstName, this._lastName, this._email,
      this._role, this._receipts, this._jwt);
  }
}
