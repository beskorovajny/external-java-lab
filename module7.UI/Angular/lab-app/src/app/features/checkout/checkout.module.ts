import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {CheckoutRoutingModule} from './checkout-routing.module';
import {CheckoutComponent} from './checkout.component';
import {CheckoutFormComponent} from './checkout-form/checkout-form.component';
import {CheckoutItemComponent} from './checkout-form/checkout-item/checkout-item.component';
import {PagesModule} from "../../pages/pages.module";
import {MatButtonModule} from "@angular/material/button";


@NgModule({
  declarations: [
    CheckoutComponent,
    CheckoutFormComponent,
    CheckoutItemComponent
  ],
  imports: [
    CommonModule,
    CheckoutRoutingModule,
    PagesModule,
    MatButtonModule
  ]
})
export class CheckoutModule {
}
