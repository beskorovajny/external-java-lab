import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {NewItemComponent} from "./pages/new-item/new-item.component";
import {ItemDetailsComponent} from "./pages/item-details/item-details.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";
import {FavoritesComponent} from "./pages/favorites/favorites.component";
import {authGuard} from "./service/auth/guard/auth.guard";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'new-item', component: NewItemComponent, canActivate: [authGuard], data: {expectedRole: 'ADMIN'}},
  {path: 'coupon/:id/details', component: ItemDetailsComponent},
  {path: 'favorites', component: FavoritesComponent},
  {
    path: 'checkout', loadChildren: () => import('./features/checkout/checkout.module')
      .then(m => m.CheckoutModule)
  },
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
