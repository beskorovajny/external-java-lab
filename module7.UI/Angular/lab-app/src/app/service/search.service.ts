import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private searchValueSubject = new BehaviorSubject<string>('');
  searchValue$: Observable<string> = this.searchValueSubject.asObservable();

  setSearchValue(value: string) {
    this.searchValueSubject.next(value);
  }
}
