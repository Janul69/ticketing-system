import { Component } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SimulationService } from '../service/simulation.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.css'
})
export class CustomerFormComponent {
  showSuccessMessage: boolean = false;
  customers: any[] = [];

  customerForm!: FormGroup

  constructor(private fb: FormBuilder, private simulationService: SimulationService) {}

  ngOnInit(): void {
    this.customerForm = this.fb.group({
      customerId: ['', [Validators.required, Validators.min(1)]],
      customerName: ['', [Validators.required, Validators.maxLength(20)]],
      vendorId: ['', [Validators.required, Validators.min(1)]],
      ticketsToBuy: ['', [Validators.required, Validators.min(1)]]
    });
  }


  onSubmit(): void {
    if (this.customerForm.valid) {
      const vendorData = this.customerForm.value;  
      this.customers.push(vendorData)
  
      this.simulationService.addCustomers(this.customers).subscribe({
        next: (response) => {
          console.log('Tickets purchased successfully', response);
          this.showSuccessMessage = true;
          this.customerForm.reset();
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
          console.log('Error buying tickets', e);
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
