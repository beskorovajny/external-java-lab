import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {Jwt} from "../../core/entity/jwt/jwt";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly baseUrl = "http://localhost:8080/api/auth";
  private readonly signUp = "/sign-up"
  private readonly signIn = "/sign-in"
  private readonly logout = "/logout"
  private readonly refreshToken = "/refresh-token"

  constructor(private http: HttpClient) {
  }

  login(credentials: { email: string; password: string }): Observable<Jwt> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post(`${this.baseUrl}` + this.signIn, credentials, {headers})
      .pipe(
        map((response: any) => {
          const jwtData = response;
          return new Jwt(
            jwtData.userEmail,
            jwtData.userRole,
            jwtData.accessToken,
            jwtData.refreshToken
          )
        })
      )
  }
}
