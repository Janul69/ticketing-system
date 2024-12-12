import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  private url = 'http://localhost:8080/config'

  constructor(private http: HttpClient) { }

  sendConfig(config: any): Observable<any> {
    return this.http.post<any>(this.url, config, { responseType: 'text' as 'json' })
  }



}
