import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  customerUrl = 'http://localhost:8200/api/customer/';
  constructor(private http: HttpClient) { }

  public getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customerUrl);
  }

  public getCustomerById(id: number): Observable<Customer>{
    return this.http.get<Customer>(this.customerUrl)
  }
}
