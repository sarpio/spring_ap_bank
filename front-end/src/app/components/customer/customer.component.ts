import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../model/Customer";
import {Account} from "../../model/Account";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  title = "Customer Module"
  customers: Customer[] = [];
  accounts: Account[] = [];
  isAnyAccount: boolean = true;

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.getAllCustomers();
  }

  getAllCustomers() {
    this.customerService.getAllCustomers().subscribe(data => {
      this.customers = data as Customer[];
    });
  }

  deleteCustomerById(customer: Customer) {
    if (customer.accounts?.length == 0) {
      this.isAnyAccount = false;
    } else {
      this.customerService.deleteCustomerById(Number(customer.id)).subscribe(res => {
        console.info(`Customer with id: ${customer.id} has been deleted`);
        this.customers = this.customers.filter((c: Customer) => c != customer)
      });
    }
  }
}
