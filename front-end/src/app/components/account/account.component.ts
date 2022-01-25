import {Component, OnInit} from '@angular/core';
import {Account} from "../../model/Account";
import {AccountService} from "../../services/account.service";
import {CustomerService} from "../../services/customer.service";
import {ActivatedRoute} from "@angular/router";
import {Customer} from "../../model/Customer";
import {Operation} from "../../model/Operation";
import {OperationsService} from "../../services/operations.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  title = 'Accounts Information';
  accounts!: Account[];
  operations: any = [];
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
              private operationService: OperationsService,
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
    if (this.operations !== null) {
      let range = this.operations.length;
      for (let i = 0; i < range; i++) {
        this.balance = this.balance + Number(this.operations[i].value)
      }
    }
  }

  deleteOperation(operation: Operation) {
    this.operationService.deleteOperation(operation.id).subscribe(() => {
    });
    this.operations = this.operations.filter((o: Operation) => o !== operation)
  }

  deleteById(id: number) {
    this.operationService.deleteOperation(id).subscribe(res => {
    });
    this.operations = this.operations.filter((item: { id: number; }) => item.id !== id);
    console.info(`Operation with id: ${id} has been cancelled`);
  }


}



