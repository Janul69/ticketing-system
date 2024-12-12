import { Injectable } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogsService {
  // private stompClient: any;
  // private logsSubject: BehaviorSubject<String[]> = new BehaviorSubject<String[]>([]);
  // public logs$ = this.logsSubject.asObservable();

  // constructor() {
  //   this.connect();
  // }

  // private connect(): void {
  //   const socket = new SockJS('http://localhost:8080/logs');
  //   this.stompClient = Stomp.over(socket);
  
  //   this.stompClient.connect({}, (frame: any) => {
  //     console.log('Connected: ' + frame);  // Log when connection is successful
  //     this.stompClient.subscribe('/topic/logs', (message: any) => {
  //       if (message.body) {
  //         const logs = this.logsSubject.getValue();
  //         logs.push(message.body);
  //         this.logsSubject.next(logs);
  //       }
  //     });
  //   }, (error: any) => {
  //     console.error('WebSocket connection error:', error);  // Log if there is a connection error
  //   });
  // }

  // public disconnect(): void {
  //   if (this.stompClient) {
  //     this.stompClient.disconnect();
  //   }
  // }
  
}
