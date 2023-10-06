import {Component, Input} from '@angular/core';
import {Tag} from "../../../core/entity/tag";
import {CheckLoginService} from "../../../service/auth/check-login.service";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Input() tags!: Tag[];


  constructor(private checkLoginService: CheckLoginService) {
  }

  checkLogin(): boolean {
    return this.checkLoginService.checkLogin();
  }

}
