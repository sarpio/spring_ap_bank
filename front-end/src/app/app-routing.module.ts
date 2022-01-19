import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CustomerComponent} from "./components/customer/customer.component";
import {AccountComponent} from "./components/account/account.component";
import {AddCustomerComponent} from "./components/customer/create/add-customer/add-customer.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {path:'', redirectTo:'/customers', pathMatch: 'full'},
  {path: 'customers', component: CustomerComponent},
  {path: 'account/:id', component: AccountComponent},
  {path: 'add-customer', component: AddCustomerComponent}
]

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
