import {Component, Input} from '@angular/core';
import {Certificate} from "../../../core/entity/certificate";
import {Router} from "@angular/router";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent {
  @Input() certificate!: Certificate;

  constructor(private router: Router) {}

  onCardClick(): void {
    this.router.navigate(['/coupon', this.certificate.id, 'details']);
  }
}
