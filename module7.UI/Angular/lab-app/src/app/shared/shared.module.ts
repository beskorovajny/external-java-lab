import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { DropdownComponent } from './dropdown/dropdown.component';



@NgModule({
  declarations: [
    NavbarComponent,
    DropdownComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavbarComponent,
    DropdownComponent
  ]
})
export class SharedModule { }
