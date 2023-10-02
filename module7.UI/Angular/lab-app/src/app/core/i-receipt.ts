import {IUser} from "./i-user";
import {ICertificate} from "./i-certificate";

export interface IReceipt {
  id: number;
  price: number;
  createDate: Date;
  user: IUser;
  certificates: Set<ICertificate>;
}
