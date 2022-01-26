import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../../../services/account.service";
import {CustomerService} from "../../../../services/customer.service";
import {Account} from "../../../../model/Account";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs";
import {Customer} from "../../../../model/Customer";

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {

  accounts: Account[] = [];
  account!: Account;
  customers: Customer[] = [];
  customer!: Customer;
  // customerId!: number;

  constructor(private accountService: AccountService, private customerService: CustomerService, private route: ActivatedRoute) {
    // this.customerId = Number(this.route.snapshot.paramMap.get('id'));
  }

  form = new FormGroup({
    accountNumber: new FormControl(null, [Validators.max(9999), Validators.min(1000)]),
    customerId: new FormControl(null, [Validators.required]),
    isForeign: new FormControl(0),
    currency: new FormControl('', [Validators.required])
  });

  ngOnInit(): void {
    this.getAllCustomers();
    // this.form.value.customerId = this.customerId;
    // this.getCustomerById(this.customerId);
    console.log(this.form.value)
  }

  private getAllCustomers() {
    return this.customerService.getAllCustomers().subscribe((res: Customer[]) => {
      this.customers = res;
    })
  }

  private getCustomerById(id: number) {
    return this.customerService.getCustomerById(id).subscribe((res: Customer) => {
      this.customer = res;
    })
  }

  onSubmit() {
    this.account = this.form.value;
    console.log(this.form.value)
    this.accountService.createAccount(this.account).subscribe(res => {
      console.log(res)
    }, error => {
      console.log(error.message)
      this.form.reset()
    })
  }
}
