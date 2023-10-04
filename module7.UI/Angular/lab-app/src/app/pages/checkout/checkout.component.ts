import {Component} from '@angular/core';
import {Certificate} from "../../core/entity/certificate";
import {ShoppingCartService} from "../../service/shopping-cart.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent/* implements OnInit*/ {
  shoppingCart: Certificate[];

  constructor(private shoppingCartService: ShoppingCartService) {
    this.shoppingCart = this.shoppingCartService.getCertificates();
  }

  /*ngOnInit(): void {
    this.shoppingCart = this.shoppingCartService.getCertificates();
    console.log(this.shoppingCart)
  }*/


}
