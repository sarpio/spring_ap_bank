import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../services/customer.service";
import {AccountService} from "../../../services/account.service";
import {Customer} from "../../../model/Customer";
import {Account} from "../../../model/Account";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {OperationsService} from "../../../services/operations.service";
import {Operation} from "../../../model/Operation";
import {HttpErrorResponse} from "@angular/common/http";
import {throwError} from "rxjs";

@Component({
  selector: 'app-add-operation',
  templateUrl: './add-operation.component.html',
  styleUrls: ['./add-operation.component.css']
})
export class AddOperationComponent implements OnInit {

  customers: Customer[] = [];
  accounts: Account[] = [];
  operation!: Operation;
  customerId: number = 0;
  accountBalance!: number;
  accountCurrency: string = '';

  form = new FormGroup({
    accountId: new FormControl(null, [Validators.required]),
    foreignAccount: new FormControl(null, [Validators.required, Validators.max(9999)]),
    value: new FormControl(null, [Validators.required]),
    type: new FormControl('', [Validators.required, Validators.max(9999)]),
  });

  constructor(private customerService: CustomerService, private accountService: AccountService, private operationService: OperationsService) {
  }

  ngOnInit() {
    this.getAllCustomers()
    this.getAccountByCustomerId(this.customerId)

  }

  getAllCustomers() {
    return this.customerService.getAllCustomers().subscribe(res => {
      this.customers = res;
    });
  }

  getAccountByCustomerId(id: number) {
    if (id > 0) {
      return this.accountService.getCustomerAccounts(id).subscribe(res => {
        console.log(res);
        this.accounts = res;
      })
    } else {
      return null;
    }
  }

  getCustomerId(event: Event) {
    let target = event.target as HTMLInputElement
    this.customerId = Number(target.value)
    console.log(this.customerId)
    this.getAccountByCustomerId(this.customerId)
  }

  getBalance(event: Event) {
    let target = event.target as HTMLInputElement
    let accountId = Number(target.value)
    this.accountService.getAccountById(accountId).subscribe(res => {
      this.accountBalance = Number(res.balance);
      this.accountCurrency = String(res.currency);
      console.log("Account balance: ", this.accountBalance)
    });
  }

  onSubmit() {
    this.operationService.addOperation(this.form.value).subscribe(next => {
    }, error => {
      if (error.status == 403) {
        alert("You can't spend more more money then you have")
        this.form.reset();
      }
    });
  }

  private static handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

}
