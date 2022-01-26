import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../../../../services/account.service";
import {CustomerService} from "../../../../../services/customer.service";
import {ActivatedRoute} from "@angular/router";
import {Customer} from "../../../../../model/Customer";
import {Account} from "../../../../../model/Account";

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {

  customer!: Customer;
  accounts: Account[] = [];
  account!: Account;
  customerId!: number;

  constructor(private accountService: AccountService,
              private customerService: CustomerService,
              private route: ActivatedRoute) {
    this.customerId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    this.getAccountByCustomerId(this.customerId)
    this.getCustomerById(this.customerId)

  }

  getCustomerById(customerId: number) {
    return this.customerService.getCustomerById(customerId).subscribe(res => {
      this.customer = res;
    });
  }

  getAccountByCustomerId(id: number) {
    return this.accountService.getCustomerAccounts(this.customerId).subscribe(res => {
      this.accounts = res;
      console.log(this.accounts)
    })
  }
}
