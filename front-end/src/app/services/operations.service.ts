import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {Operation} from "../model/Operation";

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
    return this.http.post<Operation>(this.operationUrl, operation, this.httpOptions);
  }

  deleteOperation(id: number| undefined) {
    // @ts-ignore
    return this.http.delete<void>(`${this.operationUrl}/${id}`, {responseType: 'text'}).pipe(catchError(this.errorHandler));
  }

  errorHandler(error:any) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
