import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import {PagesModule} from "../pages/pages.module";
import {RouterLink, RouterLinkActive} from "@angular/router";
import { LoginButtonComponent } from './navbar/login-button/login-button.component';
import { LogoutButtonComponent } from './navbar/logout-button/logout-button.component';
import { ShoppingCartComponent } from './navbar/shopping-cart/shopping-cart.component';
import {MatButtonModule} from "@angular/material/button";
import {MatTooltipModule} from "@angular/material/tooltip";
import { ScrollButtonComponent } from './navbar/scroll-button/scroll-button.component';


@NgModule({
  declarations: [
    NavbarComponent,
    SearchBarComponent,
    LoginButtonComponent,
    LogoutButtonComponent,
    ShoppingCartComponent,
    ScrollButtonComponent
  ],
  imports: [
    CommonModule,
    PagesModule,
    RouterLink,
    RouterLinkActive,
    NgOptimizedImage,
    MatButtonModule,
    MatTooltipModule
  ],
  exports: [
    NavbarComponent,
    NavbarComponent,
  ]
})
export class SharedModule { }
