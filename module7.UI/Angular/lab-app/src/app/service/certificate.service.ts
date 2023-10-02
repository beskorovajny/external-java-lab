import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ICertificate} from "../core/i-certificate";
import {Certificate} from "../core/entity/certificate";

@Injectable({
  providedIn: 'root'
})
export class CertificateService {
  private baseUrl = "http://localhost:8080/api/certificates";

  constructor(private http: HttpClient) {
  }

  getCertificates(): Observable<ICertificate[]> {
    return this.http.get<ICertificate[]>(`${this.baseUrl}/find-all?page=1&size=20`).pipe(
      map((response: any) => {
        const certificateList = response?._embedded?.giftCertificateModelList || [];

        return certificateList.map((certificateData: any) => {
          return new Certificate(
            certificateData.id,
            certificateData.name,
            certificateData.description,
            certificateData.duration,
            certificateData.price,
            new Date(certificateData.createDate)
          );
        });
      })
    );
  }
}
