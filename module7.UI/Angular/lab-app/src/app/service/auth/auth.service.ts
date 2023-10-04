import {HttpClient, HttpHeaders, HttpResponse, HttpStatusCode} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {map, Observable} from "rxjs";
import {Jwt} from "../../core/entity/jwt/jwt";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly baseUrl = "http://localhost:8080/api/auth";


    constructor(private http: HttpClient) {
    }

    register(credentials: {
        lastName: string, password: string,
        email: string, firstName: string
    }) {
        this.removeOldCredentials();

        const headers = new HttpHeaders({'Content-Type': 'application/json'});
        return this.http.post(`${this.baseUrl}/sign-up`, credentials, {headers})
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

    login = (credentials: { email: string; password: string }): Observable<Jwt> => {
        this.removeOldCredentials();

        const headers = new HttpHeaders({'Content-Type': 'application/json'});
        return this.http.post(`${this.baseUrl}/sign-in`, credentials, {headers})
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
    };

    logout(): Observable<boolean> {
        const userEmail = window.localStorage.getItem('user');
        if (!userEmail) {
            console.log('User email is not correct...');
            throw new Error('User email is not correct...')
        }

        const token = window.localStorage.getItem(userEmail);
        alert(token)

        const headers = new HttpHeaders()
            .set('Authorization', `Bearer ${token}`)
            .set('Content-Type', 'application/json');

        this.removeOldCredentials();

        return this.http
            .post<boolean>(`${this.baseUrl}/logout`, null, {headers, observe: 'response'})
            .pipe(
                map((response: HttpResponse<any>) => {
                    return response.status === HttpStatusCode.Ok;
                })
            )
    }


    private removeOldCredentials() {
        const oldEmail = window.localStorage.getItem('user');
        window.localStorage.removeItem('user');
        if (oldEmail) {
            window.localStorage.removeItem(oldEmail);
        }
    }
}

