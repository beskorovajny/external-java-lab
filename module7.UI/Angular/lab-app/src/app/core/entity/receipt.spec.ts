import { Receipt } from './receipt';
import {User} from "./user";
import {UserRole} from "../enum/user-role";
import {IReceipt} from "../i-receipt";
import {ICertificate} from "../i-certificate";

describe('Receipt', () => {
  const user = new User(1, 'fname', 'lname', UserRole.GUEST, new Set<IReceipt>())
  it('should create an instance', () => {
    expect(new Receipt(1, 22.5, new Date, user, new Set<ICertificate>())).toBeTruthy();
  });
});
