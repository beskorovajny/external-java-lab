import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { ItemDetailsComponent } from './item-details/item-details.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { NewItemComponent } from './new-item/new-item.component';
import { RegisterComponent } from './register/register.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './home/category/category.component';
import { CardComponent } from './home/card/card.component';
import { CategoryBoxComponent } from './home/category-box/category-box.component';
import {SharedModule} from "../shared/shared.module";



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
    CategoryBoxComponent
  ],
  exports: [
    CategoryComponent,
    CardComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PagesModule { }
