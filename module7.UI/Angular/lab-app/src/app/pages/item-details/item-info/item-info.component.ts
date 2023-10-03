import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";

@Component({
  selector: 'app-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemInfoComponent {
  @Input() certificate!: Certificate;
}
