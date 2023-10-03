import {Component, Input} from '@angular/core';
import {Tag} from "../../../../core/entity/tag";

@Component({
  selector: 'app-category-box',
  templateUrl: './category-box.component.html',
  styleUrls: ['./category-box.component.css']
})
export class CategoryBoxComponent {
  @Input() tag!: Tag;
}
