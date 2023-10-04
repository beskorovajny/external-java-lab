import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  private readonly baseUrl = "http://localhost:8080/api/receipts/create";

  constructor(private http: HttpClient) {
  }

  checkout(certificatesIds: number[]) {
    const userEmail = window.localStorage.getItem('user');
    if (!userEmail) {
      console.log('User email is not correct...');
      throw new Error('User email is not correct...')
    }

    const token = window.localStorage.getItem(userEmail);

    const headers = new HttpHeaders()
      .set('Authorization', `Bearer ${token}`)
      .set('Content-Type', 'application/json');

    const jsonObject = {
      giftCertificatesIDs: certificatesIds
    };
    const body = JSON.stringify(jsonObject);

    return this.http.post<number>(`${this.baseUrl}`, body, {headers}).pipe(map((response: any) => {
        return response.id;
      })
    );
  }
}
