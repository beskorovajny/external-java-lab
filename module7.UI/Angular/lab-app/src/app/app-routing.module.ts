import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {NewItemComponent} from "./pages/new-item/new-item.component";
import {ItemDetailsComponent} from "./pages/item-details/item-details.component";
import {CheckoutComponent} from "./pages/checkout/checkout.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";

const routes: Routes = [
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
