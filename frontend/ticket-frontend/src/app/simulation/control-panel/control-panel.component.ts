import { Component } from '@angular/core';
import { LogsComponent } from '../logs/logs.component';
import { SimulationService } from '../service/simulation.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [LogsComponent, NgIf],
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.css'
})
export class ControlPanelComponent {
  simulationStatus: String = '';

  constructor(private simulationService: SimulationService) {

  }

  startSimulation(): void {
    this.simulationService.startSimulation().subscribe({
      next: (response) => {
        console.log('Simulation started successfully', response);
        this.simulationStatus = 'Simulation started successfully.';
      },
      error: (e) => {
        console.error('Error starting simulation', e);
        this.simulationStatus = 'Error starting simulation.';
      },
    });
  }

  stopSimulation(): void {
    this.simulationService.stopSimulation().subscribe({
      next: (response) => {
        console.log('Simulation stopped successfully', response);
        this.simulationStatus = 'Simulation stopped successfully.';
      },
      error: (e) => {
        console.error('Error stopping simulation', e);
        this.simulationStatus = 'Error stopping simulation.';
      },
    });
  }

}
