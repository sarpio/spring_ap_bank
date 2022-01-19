import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../../services/customer.service";

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  public id = 0;

  constructor(private customerService: CustomerService) {
  }

  ngOnInit(): void {
  }

  onSubmit(item: any) {
    let customer: {
      name: string;
    }

    let account: {
      accountNumber: number;
      customerId: number;
      isForeign: number;
      currency: string;
      balance: number;
    }

    customer = {
      name: item.name
    }
    customer.name = item.name;

    this.createCustomer(customer)
    account = {
      accountNumber: item.accountNumber,
      customerId: this.id,
      isForeign: 0,
      currency: item.currency,
      balance: 0
    }
    this.createAccount(account);

  }

  createCustomer(values: any) {
    return this.customerService.addCustomer(values).subscribe(res => {
      this.id = Number(res.id) + 1
      console.log('new customer id is: ', this.id);

    })
  }

  createAccount(values: any) {
    console.log(values)
    return this.customerService.createAccount(values).subscribe(res => {
    })
  }

}
