import {ICertificate} from "../i-certificate";
import {ITag} from "../i-tag";

export class Certificate implements ICertificate{
  private _id: number;
  private _name: string;
  private _description: string;
  private _duration: number;
  private _price: number;
  private _createDate: Date;
  private _tags: Set<ITag> = new Set<ITag>();

  constructor(id: number,
              name: string,
              description: string,
              duration: number,
              price: number,
              createDate: Date) {
    this._id = id;
    this._name = name;
    this._description = description;
    this._duration = duration;
    this._price = price;
    this._createDate = createDate;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get duration(): number {
    return this._duration;
  }

  set duration(value: number) {
    this._duration = value;
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

  get tags(): Set<ITag> {
    return this._tags;
  }

  set tags(value: Set<ITag>) {
    this._tags = value;
  }
}

