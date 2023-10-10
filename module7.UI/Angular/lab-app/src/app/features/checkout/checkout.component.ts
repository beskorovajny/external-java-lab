import {Component} from '@angular/core';
import {ShoppingCartService} from "../../service/shopping-cart.service";
import {Certificate} from "../../core/entity/certificate";



@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  shoppingCart: Certificate[];

  constructor(private shoppingCartService: ShoppingCartService) {
    this.shoppingCart = this.shoppingCartService.getCertificates();
  }

}
