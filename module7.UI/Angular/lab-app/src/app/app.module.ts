import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from 'src/app/material-module/material-module.module';
import {SharedModule} from "src/app/shared/shared.module";
import {PagesModule} from "./pages/pages.module";
import {RouterModule} from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";
import {HomeComponent} from "./pages/home/home.component";
import {NewItemComponent} from "./pages/new-item/new-item.component";
import {ItemDetailsComponent} from "./pages/item-details/item-details.component";
import {CheckoutComponent} from "./pages/checkout/checkout.component";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    SharedModule,
    PagesModule,
    RouterModule.forRoot([
      {path: 'home', component: HomeComponent},
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegisterComponent},
      {path: 'new-item', component: NewItemComponent},
      {path: 'item-details', component: ItemDetailsComponent},
      {path: 'checkout', component: CheckoutComponent},
      {path: '', redirectTo: '/home', pathMatch: 'full'},
      {path: 'home', redirectTo: '/login', pathMatch: 'full'},
      {path: 'home', redirectTo: '/register', pathMatch: 'full'},
      {path: 'home', redirectTo: '/new-item', pathMatch: 'full'},
      {path: 'home', redirectTo: '/item-details', pathMatch: 'full'},
      {path: 'home', redirectTo: '/checkout', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
