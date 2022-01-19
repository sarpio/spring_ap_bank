import {Component, OnInit} from '@angular/core';
import {Account} from "../../model/account";
import {AccountService} from "../../services/account.service";
import {CustomerService} from "../../services/customer.service";
import {ActivatedRoute} from "@angular/router";
import {Customer} from "../../model/customer";
import {Operation} from "../../model/operation";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  title = 'Accounts Information';
  accounts!: Account[];
  operations!: any;
  account!: Account;
  customer!: Customer;
  customerName: string = '';
  accountNumber!: number;
  balance: number = 0;
  currency: string = '';
  id: number;
  balance2: number = 0;

  constructor(private accountService: AccountService,
              private customerService: CustomerService,
              private route: ActivatedRoute) {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    this.getAccountById(this.id)

  }

  getAccountById(id: number) {
    return this.accountService.getAccountById(id).subscribe((res: Account) => {
      this.accountNumber = res.accountNumber;
      this.currency = res.currency;
      this.getCustomerById(res.customerId)
      this.account = res;
      this.operations = res.operations;
      this.getBalance()
    });
  }

  getCustomerById(customerId: number) {
    return this.customerService.getCustomerById(customerId).subscribe(res => {
      this.customerName = res.name;
    })
  }

  getBalance() {
    let range = 0;
    if (this.operations.length !== null) {
      let range = this.operations.length;
      for (let i = 0; i < range; i++) {
        this.balance = this.balance + Number(this.operations[i].value)
      }
    }
  }

}



