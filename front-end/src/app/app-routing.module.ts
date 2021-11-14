import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {CustomerComponent} from "./components/customer/customer.component";

const routes: Routes = [
  {path:'', redirectTo:'/customer', pathMatch: 'full'},
  {path: 'customer', component: CustomerComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
