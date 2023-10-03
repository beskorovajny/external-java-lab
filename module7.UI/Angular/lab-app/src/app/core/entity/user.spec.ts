import {User} from './user';
import {UserRole} from "../enum/user-role";
import {IReceipt} from "../i-receipt";
import {Receipt} from "./receipt";

describe('User', () => {
  it('should create an instance', () => {
    expect(new User(1, 'f_name', 'l_name', UserRole.GUEST, new Set<Receipt>()))
      .toBeTruthy();
  });
});
