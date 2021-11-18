import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CustomerComponent} from "./components/customer/customer.component";
import {AccountComponent} from "./components/account/account.component";
import {AddCustomerComponent} from "./components/customer/create/add-customer/add-customer.component";

const routes: Routes = [
  {path:'', redirectTo:'/customers', pathMatch: 'full'},
  {path: 'customers', component: CustomerComponent},
  {path: 'accounts', component: AccountComponent},
  {path: 'add-customer', component: AddCustomerComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
