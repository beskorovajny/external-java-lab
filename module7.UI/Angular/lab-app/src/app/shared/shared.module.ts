import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchBarComponent } from './search-bar/search-bar.component';
import {PagesModule} from "../pages/pages.module";



@NgModule({
  declarations: [
    NavbarComponent,
    SearchBarComponent
  ],
    imports: [
        CommonModule,
        PagesModule
    ],
  exports: [
    NavbarComponent,
  ]
})
export class SharedModule { }
