import {UserRole} from "./enum/user-role";
import {IReceipt} from "./i-receipt";

export interface IUser {
  id: number;
  firstName: string;
  lastName: string;
  role: UserRole;
  receipts: Set<IReceipt>;
}
