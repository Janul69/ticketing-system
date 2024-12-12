import { Component } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SimulationService } from '../service/simulation.service';
import { NgIf, CommonModule } from '@angular/common';

@Component({
  selector: 'app-vendor-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, CommonModule],
  templateUrl: './vendor-form.component.html',
  styleUrl: './vendor-form.component.css'
})
export class VendorFormComponent {
  showSuccessMessage: boolean = false;
  vendors: any[] = [];

  vendorForm!: FormGroup;

  constructor(private fb: FormBuilder, private simulationService: SimulationService) {}

  ngOnInit(): void {
    this.vendorForm = this.fb.group({
      vendorId: ['', [Validators.required, Validators.min(1)]],
      eventName: ['', [Validators.required, Validators.maxLength(50)]],
      ticketsToRelease: ['', [Validators.required, Validators.min(1)]],
      ticketPrice: ['', [Validators.required, Validators.min(1)]]
    })
  }

  onSubmit(): void {
    //this.showSuccessMessage = true;
    if (this.vendorForm.valid) {
      const vendorData = this.vendorForm.value;  
      this.vendors.push(vendorData)
  
      this.simulationService.addVendors(this.vendors).subscribe({
        next: (response) => {
          console.log('Tickets added successfully', response);
          this.showSuccessMessage = true;
          this.vendorForm.reset();
          // if (response === 'Ticket added successfully') {
          //   this.showSuccessMessage = true;
          //   this.vendorForm.reset();
          //   console.log('Ticket successfully added to the backend');
            
          // } else {
          //   console.error('Unexpected response:', response);
          //   this.showSuccessMessage = false;
          // }
        },
        error: (e) => {
          console.log('Error adding tickets', e);
          this.showSuccessMessage = false;
          if (e.status === 0) {
            console.error('Network error or CORS issue');
          } else {
            console.error(`Backend returned code ${e.status}: ${e.message}`);
          }
        },
        complete: () => {
          console.info('Request completed');
        },
      });
    } else {
      console.log('Form is not valid');
    }
  }
  

}
