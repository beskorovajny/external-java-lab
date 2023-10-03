import {Component, Input} from '@angular/core';
import {Tag} from "../../../core/entity/tag";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Input() tags!: Tag[];

  checkLogin(): boolean {
    const userData = window.localStorage.getItem('user');
    return userData !== null && userData.length > 0;
  }
}
