import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private searchDataSource = new Subject<{ searchValue: string, searchOption: string }>();
  searchTerm$ = this.searchDataSource.asObservable();

  setSearchValue(searchData: { searchValue: string, searchOption: string }) {
    this.searchDataSource.next(searchData);
  }
}
