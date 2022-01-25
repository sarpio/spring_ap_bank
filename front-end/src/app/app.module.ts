import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {CustomerComponent} from './components/customer/customer.component';
import {HttpClientModule} from "@angular/common/http";
import {AccountComponent} from './components/account/account.component';
import {AddCustomerComponent} from './components/customer/create/add-customer/add-customer.component';
import {YesNoPipe} from "./yes-no.pipe";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddOperationComponent } from './components/operation/add-operation/add-operation.component';
import { AddAccountComponent } from './components/account/add/add-account/add-account.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    AccountComponent,
    AddCustomerComponent,
    YesNoPipe,
    AddOperationComponent,
    AddAccountComponent

  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
