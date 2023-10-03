import {Receipt} from './receipt';
import {User} from "./user";
import {UserRole} from "../enum/user-role";
import {Certificate} from "./certificate";

describe('Receipt', () => {
  const user = new User(1, 'fname', 'lname', UserRole.GUEST, new Set<Receipt>())
  it('should create an instance', () => {
    expect(new Receipt(1, 22.5, new Date, user, new Set<Certificate>())).toBeTruthy();
  });
});
