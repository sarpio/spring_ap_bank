import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, retry} from "rxjs";
import {Operation} from "../model/operation";

@Injectable({
  providedIn: 'root'
})
export class OperationsService {

  operationUrl = 'http://localhost:8300/api/operation'

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) {
  }

  addOperation(operation: Operation): Observable<Operation> {
    return this.http.post<Operation>(this.operationUrl, operation, this.httpOptions)
  }

  deleteOperation(id: number) {
    this.http.delete<void>(`${this.operationUrl}/${id}`, this.httpOptions).subscribe(res=>{
      console.log(`Operation with id: ${id} has been deleted`)
    })
  }
}
