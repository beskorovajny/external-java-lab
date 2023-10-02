import {UserRole} from "../enum/user-role";
import {Receipt} from "./receipt";
import {IUser} from "../i-user";
import {IReceipt} from "../i-receipt";
import Set from "$GLOBAL$";

export class User implements IUser{
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _role: UserRole;
  private _receipts: Set<IReceipt> = new Set<IReceipt>();

  constructor(id: number, firstName: string, lastName: string, role: UserRole, receipts: Set<Receipt>) {
    this._id = id;
    this._firstName = firstName;
    this._lastName = lastName;
    this._role = role;
    this._receipts = receipts;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get lastName(): string {
    return this._lastName;
  }

  set lastName(value: string) {
    this._lastName = value;
  }

  get role(): UserRole {
    return this._role;
  }

  set role(value: UserRole) {
    this._role = value;
  }

  get receipts(): Set<IReceipt> {
    return this._receipts;
  }

  set receipts(value: Set<IReceipt>) {
    this._receipts = value;
  }
}
