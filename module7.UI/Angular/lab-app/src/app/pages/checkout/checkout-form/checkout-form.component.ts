import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";
import {CheckoutService} from "../../../service/checkout.service";
import {Location} from '@angular/common';
import {Router} from "@angular/router";
import {ShoppingCartService} from "../../../service/shopping-cart.service";

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css']
})
export class CheckoutFormComponent {
  @Input() certificates: Certificate[] = [];
  private totalPrice: number = 0;

  constructor(private checkoutService: CheckoutService,
              private location: Location,
              private router: Router,
              private cartService: ShoppingCartService) {
  }

  calculateTotalPrice() {
    if (this.certificates.length > 0) {
      this.totalPrice = this.certificates
        .reduce((accumulator, certificate) =>
          accumulator + certificate.price, 0);
    }
    return this.totalPrice;
  }

  onCheckout() {
    if (this.certificates.length > 0) {
      const certificatesIds: number[] = this.certificates.map((certificate) => {
        return certificate.id;
      })

      this.checkoutService.checkout(certificatesIds).subscribe((receiptId) => {

          alert(`Success, your receipt ID: ${receiptId}`);

          this.cartService.clearCart();
          this.router.navigate(['/home']);
        },
        error => {
          console.error('Checkout failed', error);
        }
      );
    }
  }

  goBack(): void {
    this.location.back();
  }

  emptyCart() {
    return this.certificates.length <= 0;
  }
}
