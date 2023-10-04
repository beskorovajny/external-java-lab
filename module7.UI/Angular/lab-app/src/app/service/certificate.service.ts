import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Certificate} from "../core/entity/certificate";

@Injectable({
    providedIn: 'root'
})
export class CertificateService {
    private baseUrl = "http://localhost:8080/api/certificates";

    constructor(private http: HttpClient) {
    }

    getCertificates(): Observable<Certificate[]> {
        return this.http.get<Certificate[]>(`${this.baseUrl}/find-all?page=0&size=20`)
            .pipe(
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

    getCertificateById(id: number) {
        const headers = new HttpHeaders({'Content-Type': 'application/json'});
        return this.http.get(`${this.baseUrl}/find/${id}`, {headers})
            .pipe(
                map((response: any) => {
                    const certificateData = response;
                    return new Certificate(
                        certificateData.id,
                        certificateData.name,
                        certificateData.description,
                        certificateData.duration,
                        certificateData.price,
                        new Date(certificateData.createDate)
                    )
                })
            )
    }
}
