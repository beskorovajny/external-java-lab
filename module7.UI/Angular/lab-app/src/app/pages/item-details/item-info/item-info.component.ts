import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";
import {ShoppingCartService} from "../../../service/shopping-cart.service";
import {CheckLoginService} from "../../../service/auth/check-login.service";

@Component({
  selector: 'app-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemInfoComponent {
  @Input() certificate!: Certificate;

  constructor(private shoppingCartService: ShoppingCartService,
              private checkLoginService: CheckLoginService) {
  }

  onAddToCart() {
    this.shoppingCartService.addCertificate(this.certificate);
  }

  checkLogin(): boolean {
    return this.checkLoginService.checkLogin();
  }
}
