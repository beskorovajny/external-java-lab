import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css']
})
export class CheckoutFormComponent {
  @Input() certificates: Certificate[] = [];
  private totalPrice: number = 0;

  calculateTotalPrice() {
    if (this.certificates.length > 0) {
      this.totalPrice = this.certificates
        .reduce((accumulator, certificate) =>
          accumulator + certificate.price, 0);
    }
    return this.totalPrice;
  }
}
