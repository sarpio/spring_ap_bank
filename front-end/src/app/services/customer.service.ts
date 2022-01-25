import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Customer} from "../model/Customer";
import {Account} from "../model/Account";

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
    return this.http.post<Customer>(this.customerUrl, JSON.stringify(customer), this.httpOptions);
  }

  public deleteCustomerById(id: number) {
    // @ts-ignore
    return this.http.delete<void>(`${this.customerUrl}/${id}`, {responseType: 'text'})
      .pipe(catchError(this.errorHandler));
  }

  errorHandler(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
