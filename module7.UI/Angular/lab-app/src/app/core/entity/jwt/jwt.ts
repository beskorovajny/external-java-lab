import {UserRole} from "../../enum/user-role";

export class Jwt {
  private _userEmail: string;
  private _userRole: UserRole;
  private _accessToken: string;
  private _refreshToken: string;


  constructor(userEmail: string, userRole: UserRole, accessToken: string, refreshToken: string) {
    this._userEmail = userEmail;
    this._userRole = userRole;
    this._accessToken = accessToken;
    this._refreshToken = refreshToken;
  }


  get userEmail(): string {
    return this._userEmail;
  }

  get userRole(): UserRole {
    return this._userRole;
  }

  get accessToken(): string {
    return this._accessToken;
  }

  get refreshToken(): string {
    return this._refreshToken;
  }
}
