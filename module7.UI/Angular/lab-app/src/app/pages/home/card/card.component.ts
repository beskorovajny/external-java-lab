import {Component, Input} from '@angular/core';
import {ICertificate} from "../../../core/i-certificate";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() certificate!: ICertificate;
}
