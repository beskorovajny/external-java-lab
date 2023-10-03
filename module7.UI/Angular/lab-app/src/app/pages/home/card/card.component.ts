import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() certificate!: Certificate;
}
