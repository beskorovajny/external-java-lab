import {Component, Input, OnInit} from '@angular/core';
import {Tag} from "../../../../core/entity/tag";
import {Subject} from "rxjs";
import {CheckLoginService} from "../../../../service/auth/check-login.service";
import {SearchService} from "../../../../service/search.service";

@Component({
  selector: 'app-category-box',
  templateUrl: './category-box.component.html',
  styleUrls: ['./category-box.component.css']
})
export class CategoryBoxComponent implements OnInit{
  @Input() tag!: Tag;

  private readonly selectedCategory = 'Tags';

  searchInput$ = new Subject<{ searchValue: string, searchOption: string }>()

  ngOnInit() {
    this.searchInput$
      .subscribe((searchTerm) => {
        this.searchService.setSearchValue(searchTerm);
      });
  }


  constructor(private searchService: SearchService) {
  }

  selectTag(tagName: string) {
    this.searchInput$.next({searchValue: tagName, searchOption: this.selectedCategory});
  }

}
