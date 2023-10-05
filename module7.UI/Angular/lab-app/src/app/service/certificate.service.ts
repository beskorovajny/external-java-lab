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

    save(data: {
        name: string,
        description: string,
        categories: string,
        validTo: Date,
        price: number
    }) {
        const userEmail = window.localStorage.getItem('user');
        if (!userEmail) {
            console.log('User email is not correct...');
            throw new Error('User email is not correct...')
        }

        const token = window.localStorage.getItem(userEmail);

        const headers = new HttpHeaders()
            .set('Authorization', `Bearer ${token}`)
            .set('Content-Type', 'application/json');
        const date = new Date(data.validTo);
        const duration = this.convertDateToDuration(date);
        const tags = this.convertStringToTagsArray(data.categories);

        const jsonObject = {
            name: data.name,
            description: data.description,
            price: data.price,
            duration: duration,
            tags: tags,

        };

        const body = JSON.stringify(jsonObject);
        alert(`Body is: ${body}`);

        return this.http.post<number>(`${this.baseUrl}/create`, body, {headers, withCredentials: true})
            .pipe(map((response: any) => {
                    return response.id;
                })
            );
    }

    convertDateToDuration(futureDate: Date) {
        const timeDifference = futureDate.getTime() - new Date().getTime();

        return Math.ceil(timeDifference / (1000 * 60 * 60 * 24));
    }


    convertStringToTagsArray(input: string): { name: string; } [] {
        const array = input.split(',');
        return array.map(tagName => ({name: tagName}));
    }
}
