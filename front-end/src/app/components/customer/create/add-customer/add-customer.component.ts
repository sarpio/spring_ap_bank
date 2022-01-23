import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../../services/customer.service";
import {NgForm} from "@angular/forms";
import {AccountService} from "../../../../services/account.service";
import {Account} from "../../../../model/account";


// import {Account} from "../../../../model/account";


@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  customerName!: string;
  accountNumber!: number;
  currency!: string;
  myCustomer: Customer = {
    name: ''
  }

  myCustomerId: number = 0;

  constructor(private customerService: CustomerService, private accountService: AccountService) {
  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    this.myCustomer.name = this.customerName
    console.log(this.myCustomer)
    this.customerService.addCustomer(this.myCustomer).subscribe(res => {
      this.myCustomerId = Number(res.id);
      console.log("Customer id: ", this.myCustomerId)

      let a: Account = {
        accountNumber: Number(this.accountNumber),
        customerId: Number(res.id),
        currency: String(this.currency),
        isForeign:0,
        balance:0
      }
      this.accountService.createAccount(a)
        .subscribe(
          res => {
        console.log(res)
      },
          error => console.error(error))
    });

  }
}

export class Customer {
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}

// export class Account {
//   accountNumber: number;
//   customerId: number;
//   currency: string;
//
//
//   constructor(accountNumber: number, customerId: number, currency: string) {
//     this.accountNumber = accountNumber;
//     this.customerId = customerId;
//     this.currency = currency;
//
//   }
// }
