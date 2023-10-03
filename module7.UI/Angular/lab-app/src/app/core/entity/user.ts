import {UserRole} from "../enum/user-role";
import {Receipt} from "./receipt";
import {Jwt} from "./jwt/jwt";

export class User {
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _email: string;
  private _role: UserRole;
  private _jwt: Jwt;
  private _receipts: Set<Receipt> = new Set<Receipt>();

  constructor(id: number,
              firstName: string,
              lastName: string,
              email: string,
              role: UserRole,
              receipts: Set<Receipt>,
              jwt: Jwt) {
    this._id = id;
    this._firstName = firstName;
    this._lastName = lastName;
    this._email = email;
    this._role = role;
    this._receipts = receipts;
    this._jwt = jwt;
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

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get role(): UserRole {
    return this._role;
  }

  set role(value: UserRole) {
    this._role = value;
  }

  get jwt(): Jwt | undefined {
    return this._jwt;
  }

  set jwt(value: Jwt) {
    this._jwt = value;
  }

  get receipts(): Set<Receipt> {
    return this._receipts;
  }

  set receipts(value: Set<Receipt>) {
    this._receipts = value;
  }
}
