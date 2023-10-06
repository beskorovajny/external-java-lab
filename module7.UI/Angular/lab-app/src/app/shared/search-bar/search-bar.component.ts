import {Component, ElementRef, ViewChild} from '@angular/core';
import {SearchService} from "../../service/search.service";

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  selectedCategory = 'All certificates'
  isCategoryListOpen: boolean = false;
  options: string[] = ['All', 'Name', 'Description', 'Tags'];


  constructor(private searchService: SearchService) {
  }

  onSearchInput(value: string) {
    this.searchService.setSearchValue(value);
  }


  toggleCategoryList(): void {
    this.isCategoryListOpen = !this.isCategoryListOpen;
  }

  selectOption(option: string): void {
    this.selectedCategory = option;
    this.isCategoryListOpen = false;
  }
}
