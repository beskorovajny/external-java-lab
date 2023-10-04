import {Tag} from "./tag";

export class Certificate {
   id: number;
   name: string;
   description: string;
   duration: number;
   price: number;
   createDate: Date;
   tags: Set<Tag> = new Set<Tag>();

  constructor(id: number,
              name: string,
              description: string,
              duration: number,
              price: number,
              createDate: Date) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.duration = duration;
    this.price = price;
    this.createDate = createDate;
  }

  equals(other: Certificate): boolean {
    return this.id === other.id;
  }
}

