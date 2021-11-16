import {Component, OnInit} from '@angular/core';
import {Account} from "../../model/account";
import {Customer} from "../../model/customer";
import {AccountService} from "../../services/account.service";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  title = 'Accounts Module';
  customer: Customer = {accounts: [], id: 0, name: ""};
  accounts: Account[] = [];

  constructor(private accountService: AccountService, private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.fetchAccounts()
  }

  fetchAccounts() {
    this.accountService.getAllAccounts().subscribe((data: Account[]) => {
      console.log(data);
      this.accounts = data;
    }, error => {
      console.log(error)
    });
  }


}
