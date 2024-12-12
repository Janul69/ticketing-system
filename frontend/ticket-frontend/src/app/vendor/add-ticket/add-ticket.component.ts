import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { VendorService } from '../service/vendor.service';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-ticket',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit{

  constructor(private fb : FormBuilder, private vendorService: VendorService) {}

  addTicketForm!: FormGroup;

  ngOnInit(): void {
    this.addTicketForm = this.fb.group({
      eventName: ['', [Validators.required, Validators.maxLength(50)]],
      ticketPrice: ['', [Validators.required, Validators.min(1)]],
      ticketsToRelease: ['', [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.addTicketForm.valid) {
      const ticketData = this.addTicketForm.value;
  
     
      const vendorId = 1; 
  

      const ticketDataWithVendorId = { ...ticketData, vendorId };
  
      this.vendorService.addTickets(ticketDataWithVendorId).subscribe({
        next: (response) => {
          console.log('Tickets added successfully', response);
          if (response === 'Ticket added successfully') {
            console.log('Ticket successfully added to the backend');
          } else {
            console.error('Unexpected response:', response);
          }
        },
        error: (e) => {
          console.log('Error adding tickets', e);
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