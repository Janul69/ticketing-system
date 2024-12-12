import { Component } from '@angular/core';
import { VendorFormComponent } from '../vendor-form/vendor-form.component';
import { CustomerFormComponent } from '../customer-form/customer-form.component';
import { ControlPanelComponent } from '../control-panel/control-panel.component';

@Component({
  selector: 'app-simulation',
  standalone: true,
  imports: [VendorFormComponent, CustomerFormComponent, ControlPanelComponent],
  templateUrl: './simulation.component.html',
  styleUrl: './simulation.component.css'
})
export class SimulationComponent {

}
