import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Certificate} from "../core/entity/certificate";

@Injectable({
  providedIn: 'root'
})
export class CertificateService {
  private readonly baseUrl = "http://localhost:8080/api/certificates";
  private defaultSize = 20;

  constructor(private http: HttpClient) {
  }

  getCertificates(page: number): Observable<Certificate[]> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.get<Certificate[]>(`${this.baseUrl}/find-all?page=${page}&size=${this.defaultSize}`
      , {headers})
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

  getCertificatesByTags(page: number, searchInputTags: string) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    const tagsArray = searchInputTags.split(',');
    const body = JSON.stringify(tagsArray);

    return this.http.post<number>(`${this.baseUrl}/find-by-tags?page=${page}&size=${this.defaultSize}`
      , body, {headers})
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

  getCertificatesByNameLike(page: number, searchInputName: string) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http
      .get<Certificate[]>(`${this.baseUrl}/find?name=${searchInputName}&page=${page}&size=${this.defaultSize}`,
        {headers})
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

  getCertificatesByDescription(page: number, searchInputDescription: string) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http
      .get<Certificate[]>(`${this.baseUrl}/find-all-with-params?tagName=&name=&` +
        `description=${searchInputDescription}&sortByName=&sortByDate=DESC` +
        `&page=${page}&size=${this.defaultSize}`,
        {headers})
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
    const tags = this.convertStringToTagsArrayWithName(data.categories);

    const jsonObject = {
      name: data.name,
      description: data.description,
      price: data.price,
      duration: duration,
      tags: tags,

    };

    const body = JSON.stringify(jsonObject);

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


  convertStringToTagsArrayWithName(input: string): { name: string; } [] {
    const array = input.split(',');
    return array.map(tagName => ({name: tagName}));
  }
}
