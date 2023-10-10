import {Component, Input} from '@angular/core';
import {Certificate} from "../../../../core/entity/certificate";



@Component({
  selector: 'app-checkout-item',
  templateUrl: './checkout-item.component.html',
  styleUrls: ['./checkout-item.component.css']
})
export class CheckoutItemComponent {
  @Input() certificate!: Certificate;
}
