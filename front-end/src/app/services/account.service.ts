import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountUrl = 'http://localhost:8100/api/account/';

  constructor(private http: HttpClient) {
  }

  public getAllAccounts(): Observable<any[]> {
    return this.http.get<any[]>(this.accountUrl);

  }
}

