import {ICertificate} from "../i-certificate";
import {User} from "./user";
import {Certificate} from "./certificate";

export class Receipt {
  private _id: number;
  private _price: number;
  private _createDate: Date;
  private _user: User;
  private _certificates: Set<Certificate>;


  constructor(id: number, price: number, createDate: Date, user: User, certificates: Set<Certificate>) {
    this._id = id;
    this._price = price;
    this._createDate = createDate;
    this._user = user;
    this._certificates = certificates;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  get createDate(): Date {
    return this._createDate;
  }

  set createDate(value: Date) {
    this._createDate = value;
  }

  get user(): User {
    return this._user;
  }

  set user(value: User) {
    this._user = value;
  }

  get certificates(): Set<Certificate> {
    return this._certificates;
  }

  set certificates(value: Set<Certificate>) {
    this._certificates = value;
  }
}
