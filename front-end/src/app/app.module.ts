import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CustomerComponent } from './components/customer/customer.component';
import {HttpClientModule} from "@angular/common/http";
import { AccountComponent } from './components/account/account.component';
import { YesNoPipe } from './components/customer/yes-no.pipe';
import { AddCustomerComponent } from './components/customer/create/add-customer/add-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    AccountComponent,
    YesNoPipe,
    AddCustomerComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
