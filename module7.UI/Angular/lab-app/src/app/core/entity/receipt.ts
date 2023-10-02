import {IReceipt} from "../i-receipt";
import {ICertificate} from "../i-certificate";
import {IUser} from "../i-user";

export class Receipt implements IReceipt{
  private _id: number;
  private _price: number;
  private _createDate: Date;
  private _user: IUser;
  private _certificates: Set<ICertificate>;


  constructor(id: number, price: number, createDate: Date, user: IUser, certificates: Set<ICertificate>) {
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

  get user(): IUser {
    return this._user;
  }

  set user(value: IUser) {
    this._user = value;
  }

  get certificates(): Set<ICertificate> {
    return this._certificates;
  }

  set certificates(value: Set<ICertificate>) {
    this._certificates = value;
  }
}
