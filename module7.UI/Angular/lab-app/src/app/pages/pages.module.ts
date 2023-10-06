import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login/login.component';
import {ItemDetailsComponent} from './item-details/item-details.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {NewItemComponent} from './new-item/new-item.component';
import {RegisterComponent} from './register/register.component';
import {LoginFormComponent} from './login/login-form/login-form.component';
import {HomeComponent} from './home/home.component';
import {CategoryComponent} from './home/category/category.component';
import {CardComponent} from './home/card/card.component';
import {CategoryBoxComponent} from './home/category/category-box/category-box.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ItemFormComponent} from './new-item/item-form/item-form.component';
import {ItemInfoComponent} from './item-details/item-info/item-info.component';
import {CheckoutItemComponent} from './checkout/checkout-form/checkout-item/checkout-item.component';
import {CheckoutFormComponent} from './checkout/checkout-form/checkout-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {FavoritesComponent} from './favorites/favorites.component';
import {MatButtonModule} from "@angular/material/button";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {PriceFormatPipe} from "./pipe/price-format.pipe";


@NgModule({
  declarations: [
    LoginComponent,
    ItemDetailsComponent,
    CheckoutComponent,
    NewItemComponent,
    RegisterComponent,
    LoginFormComponent,
    HomeComponent,
    CategoryComponent,
    CardComponent,
    CategoryBoxComponent,
    PageNotFoundComponent,
    ItemFormComponent,
    ItemInfoComponent,
    CheckoutItemComponent,
    CheckoutFormComponent,
    FavoritesComponent,
    PriceFormatPipe
  ],
  exports: [
    CategoryComponent,
    CardComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatButtonModule,
    MatTooltipModule,
    MatDatepickerModule
  ]
})
export class PagesModule {
}
