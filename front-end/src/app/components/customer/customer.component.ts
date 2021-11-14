import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../model/customer";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  title = "Customer Module"

  customers: Customer[] = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getAllCustomers().subscribe((data: any[])=> {
      console.log(data)
      this.customers = data;
    })
  }

}
