import { Component } from '@angular/core';
import { FormGroup, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ConfigurationService } from '../service/configuration.service';

@Component({
  selector: 'app-configuration',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './configuration.component.html',
  styleUrl: './configuration.component.css'
})
export class ConfigurationComponent {
  configForm!: FormGroup;

  constructor(private fb: FormBuilder, private configService: ConfigurationService) {}

  ngOnInit(): void {
    this.configForm = this.fb.group({
      totalTicekts: ['', [Validators.required, Validators.min(1)]],
      ticketReleaseRate: ['', [Validators.required, Validators.min(1)]],
      customerRetrievalRate: ['', [Validators.required, Validators.min(1)]],
      maxTicketCapacity: ['', [Validators.required, Validators.min(1)]],
    })
  }

  onSubmit(): void {
    if (this.configForm.valid) {
      const config = this.configForm.value;

      this.configService.sendConfig(config).subscribe({
        next: (response) => {
          console.log('Config send successfully', response);
          this.configForm.reset();
        },
        error: (e) => {
          console.log('Error sending config', e);
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

