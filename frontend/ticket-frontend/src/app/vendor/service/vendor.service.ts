import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class VendorService {

  private url = 'http://localhost:8080/api/vendor/add';

  constructor(private http: HttpClient) { }

  addTickets(ticketData: any): Observable<any> {
    return this.http.post<any>(this.url, ticketData, { responseType: 'text' as 'json' });
  }
}
