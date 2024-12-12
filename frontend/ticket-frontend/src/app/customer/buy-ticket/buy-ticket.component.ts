import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CustomerService } from '../service/customer.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-buy-ticket',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './buy-ticket.component.html',
  styleUrl: './buy-ticket.component.css'
})
export class BuyTicketComponent implements OnInit{

  constructor(private fb : FormBuilder, private customerService: CustomerService) {}

  buyTicketForm!: FormGroup;

  ngOnInit(): void {
      this.buyTicketForm = this.fb.group({
        customerName: ['', [Validators.required, Validators.maxLength(50)]],
        ticketsToBuy: ['', [Validators.required, Validators.min(1)]]   
      })
  }

  onSubmit() {
    if (this.buyTicketForm.valid) {
      const ticketData = this.buyTicketForm.value;

      const customerId = 1;
      const vendorId = 1;

      const ticketDataWithCustomer = { ...ticketData, customerId, vendorId};

      this.customerService.buyTicket(ticketDataWithCustomer).subscribe({
        next: (response) => {
          console.log('Ticket purchase completed', response);
          if (response === 'Ticket purchase completed') {
            console.log('Ticket successfully purchased');
          } else {
            console.error('Unexpected response: ', response);
            
          }
        },
        error: (e) => {
          console.log('Error purchasing tickets', e);
          if (e.status == 0) {
            console.error('Network error or CORS issue');  
          } else {
            console.error(`Backend returned error: ${e.status}: ${e.message}`);
            
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
