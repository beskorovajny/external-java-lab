import {Component, OnInit} from '@angular/core';
import {SearchService} from "../../service/search.service";
import {debounceTime, Subject} from "rxjs";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {
  selectedCategory = 'All';

  isCategoryListOpen: boolean = false;
  options: string[] = ['All', 'Name', 'Description', 'Tags'];

  searchInput$ = new Subject<{ searchValue: string, searchOption: string }>()


  constructor(private searchService: SearchService) {
  }

  ngOnInit(): void {
    this.searchInput$
      .pipe(debounceTime(500))
      .subscribe((searchTerm) => {
        this.searchService.setSearchValue(searchTerm,
        )
        ;
      });
  }

  onInputChange(event: any) {
    const searchValue = event.target.value;
    this.searchInput$.next({searchValue: searchValue, searchOption: this.selectedCategory});
  }


  toggleCategoryList(): void {
    this.isCategoryListOpen = !this.isCategoryListOpen;
  }

  selectOption(option: string): void {
    this.selectedCategory = option;
    this.isCategoryListOpen = false;
  }
}
