import {ITag} from "./i-tag";

export interface ICertificate {
  id: number;
  name: string;
  description: string;
  duration: number;
  price: number;
  createDate: Date;
  tags: Set<ITag>;
}
