import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../../../services/customer.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  constructor(private customerService: CustomerService) {
  }

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.min(3)]),
    accountName: new FormControl(''),
    currency: new FormControl('', [Validators.required])
  })

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.form.value)
    this.customerService.addCustomer(this.form.value).subscribe(res => {
      console.log(res)
    });
  }
}
