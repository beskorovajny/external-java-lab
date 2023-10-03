import { Component } from '@angular/core';
import {User} from "../../../core/entity/user";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  checkLogin(): boolean {
    const userData = window.localStorage.getItem('user');
    return userData !== null && userData.length > 0;
  }
}
