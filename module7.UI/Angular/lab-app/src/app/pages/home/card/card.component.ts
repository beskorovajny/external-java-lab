import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";
import {Router} from "@angular/router";
import {ShoppingCartService} from "../../../service/shopping-cart.service";
import {CheckLoginService} from "../../../service/auth/check-login.service";
import {FavoriteService} from "../../../service/favorite.service";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() certificate!: Certificate;

  constructor(private router: Router,
              private shoppingCartService: ShoppingCartService,
              private checkLoginService: CheckLoginService,
              private favoritesService: FavoriteService) {
  }


  onCardClick(): void {
    this.router.navigate(['/coupon', this.certificate.id, 'details']);
  }

  onAddToCart() {
    this.shoppingCartService.addCertificate(this.certificate);
  }

  onAddToFavorite() {
    this.favoritesService.addCertificate(this.certificate);
  }

  loggedIn() {
    return this.checkLoginService.checkLogin();
  }
}
