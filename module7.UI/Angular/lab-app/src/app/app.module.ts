import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from 'src/app/material-module/material-module.module';
import {SharedModule} from "src/app/shared/shared.module";
import {PagesModule} from "./pages/pages.module";
import {RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    SharedModule,
    PagesModule,
    HttpClientModule,
    RouterModule
  ],
  providers: [],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
