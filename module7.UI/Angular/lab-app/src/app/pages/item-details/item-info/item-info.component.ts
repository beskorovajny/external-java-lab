import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";
import {ShoppingCartService} from "../../../service/shopping-cart.service";

@Component({
  selector: 'app-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemInfoComponent {
  @Input() certificate!: Certificate;

  constructor(private shoppingCartService: ShoppingCartService) {
  }

  onAddToCart() {
    this.shoppingCartService.addCertificate(this.certificate);
  }
}
