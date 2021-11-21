import {Component, OnInit} from '@angular/core';
import {Account} from "../../model/account";
import {AccountService} from "../../services/account.service";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  title = 'Accounts Module';
  accounts: Account[] = [];
  customer: any;

  constructor(private accountService: AccountService, private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.fetchAccounts()
  }

  fetchAccounts() {
    this.accountService.getAllAccounts().subscribe((data: Account[]) => {
      this.accounts = data;
        const accountsArray: any[] = Array.of(data);
        console.log(accountsArray)
    }, error => {
      console.log(error)
    });
  }

  fetchCustomerById(id: number) {
    return this.customerService.getCustomerById(id);
  }
}


