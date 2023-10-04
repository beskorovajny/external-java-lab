import { NgModule } from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import {PagesModule} from "../pages/pages.module";
import {RouterLink, RouterLinkActive} from "@angular/router";
import { LoginButtonComponent } from './navbar/login-button/login-button.component';
import { LogoutButtonComponent } from './navbar/logout-button/logout-button.component';



@NgModule({
  declarations: [
    NavbarComponent,
    SearchBarComponent,
    LoginButtonComponent,
    LogoutButtonComponent
  ],
  imports: [
    CommonModule,
    PagesModule,
    RouterLink,
    RouterLinkActive,
    NgOptimizedImage
  ],
  exports: [
    NavbarComponent,
    NavbarComponent,
  ]
})
export class SharedModule { }
