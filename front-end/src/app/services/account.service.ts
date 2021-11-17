import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Account} from "../model/account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  accountUrl ='http://localhost:8100/api/account/';

  constructor(private http: HttpClient) { }

  public getAllAccounts():Observable<any[]> {
    return  this.http.get<any[]>(this.accountUrl);

  }
}

