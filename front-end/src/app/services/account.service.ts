import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {Account} from "../model/Account";


@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountUrl = 'http://localhost:8100/api/account';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  httpTextResponse = {
    headers: new HttpHeaders({
      'Content-Type': 'text'
    })
  }
  constructor(private http: HttpClient) {
  }

  getAllAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.accountUrl)
      .pipe(retry(1));
  }

  getAccountById(id: number): Observable<Account> {
    return this.http.get<Account>(`${(this.accountUrl)}/${id}`)
      .pipe(retry(1));
  }

  createAccount(account: Account): Observable<Account>{
    return this.http.post<Account>(this.accountUrl, account, this.httpOptions);
  }

  getCustomerAccounts(id: number): Observable<Account[]> {
    return this.http.get<Account[]>(`${(this.accountUrl)}/customer/${id}`)
      .pipe(retry(1))
  }

  deleteAccountById(id: number | undefined) {
    // @ts-ignore
    return this.http.delete<void>(`${this.accountUrl}/${id}`, {responseType:'text'})
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

