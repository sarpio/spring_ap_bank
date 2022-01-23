import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, retry} from "rxjs";
import {Account} from "../model/account";

const accountUrl = 'http://localhost:8100/api/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) {
  }

  getAllAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(accountUrl).pipe(retry(1));
  }

  getAccountById(id: number): Observable<Account> {
    return this.http.get<Account>(`${accountUrl}/${id}`).pipe(retry(1));
  }

  createAccount(account: Account): Observable<Account>{
    return this.http.post<Account>(accountUrl, account, this.httpOptions);
  }

}

