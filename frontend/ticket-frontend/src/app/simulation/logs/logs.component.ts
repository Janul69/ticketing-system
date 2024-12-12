import { Component, OnDestroy, OnInit } from '@angular/core';
import { LogsService } from './logs.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-logs',
  standalone: true,
  imports: [NgFor],
  templateUrl: './logs.component.html',
  styleUrl: './logs.component.css'
})
export class LogsComponent {
  // logs: String[] = [];

  // constructor(private logsService: LogsService) {}

  // ngOnInit(): void {
  //   this.logsService.logs$.subscribe((logs) => {
  //     this.logs = logs;
  //   });   
  // }

  // ngOnDestroy(): void {
  //   this.logsService.disconnect();
  // }
  

}
