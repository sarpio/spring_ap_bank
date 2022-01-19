import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer";
import {Account} from "../model/account";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  accountUrl = 'http://localhost:8100/api/account/';
  customerUrl = 'http://localhost:8200/api/customer/';

  constructor(private http: HttpClient) {
  }

  public getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.customerUrl);
  }

  public getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(this.customerUrl + id);
  }

  public addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.customerUrl, JSON.stringify(customer), this.httpOptions)
  }

  public createAccount(account: Account): Observable<Account>{
    return this.http.post<Account>(this.accountUrl, JSON.stringify(account), this.httpOptions)
  }

}
