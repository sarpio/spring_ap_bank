import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, retry} from "rxjs";
import {Account} from "../model/account";

const accountUrl = 'http://localhost:8100/api/account';
const customerUrl = 'http://localhost:8200/api/customer/';

@Injectable({
  providedIn: 'root'
})
export class AccountService {


  constructor(private http: HttpClient) {
  }

  getAllAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(accountUrl).pipe(retry(1));
  }

  getAccountById(id: number): Observable<Account> {
    return this.http.get<Account>(`${accountUrl}/${id}`).pipe(retry(1));
  }

}

