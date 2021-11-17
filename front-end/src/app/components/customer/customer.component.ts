import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../model/customer";
import {Account} from "../../model/account";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  title = "Customer Module"
  customers: Customer[] = [];
  accounts: Account[] = [];

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.getAllCustomers();
  }

  getAllCustomers() {
    this.customerService.getAllCustomers().subscribe(data => {
      this.customers = data as Customer[];
      console.log(this.customers)
    });
  }
}
