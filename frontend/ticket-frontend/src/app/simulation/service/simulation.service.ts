import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {

  private baseUrl = 'http://localhost:8080/simulation';

  constructor(private http: HttpClient) { }

  addVendors(vendors: any[]): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/vendors`, vendors, { responseType: 'text' as 'json' });
  }

  addCustomers(customers: any[]): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/customers`, customers, { responseType: 'text' as 'json' })

  }

  startSimulation(): Observable<any> {
    return this.http.post(`${this.baseUrl}/start`, null, { responseType: 'text' as 'json' })
  }

  stopSimulation(): Observable<any> {
    return this.http.post(`${this.baseUrl}/stop`, null, { responseType: 'text' as 'json' });
  }

}
