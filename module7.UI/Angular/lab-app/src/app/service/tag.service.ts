import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Tag} from "../core/entity/tag";

@Injectable({
    providedIn: 'root'
})
export class TagService {
    private baseUrl = "http://localhost:8080/api/tags";
    private readonly defaultSize: number = 8;

    constructor(private http: HttpClient) {
    }

    getTags(): Observable<Tag[]> {
        const userEmail = window.localStorage.getItem('user');
        let token;
        if (userEmail) {
            token = window.localStorage.getItem(userEmail);
        }
        const headers = new HttpHeaders()
            .set('Authorization', `Bearer ${token}`)
            .set('Content-Type', 'application/json');

        return this.http.get<Tag[]>(`${this.baseUrl}/find-all?page=0&size=${this.defaultSize}`,
          {headers, withCredentials: true})
            .pipe(
                map((response: any) => {
                    const tagList = response?._embedded?.tagModelList || [];

                    return tagList.map((tagData: any) => {
                        return new Tag(
                            tagData.id,
                            tagData.name
                        );
                    });
                })
            );
    }
}
