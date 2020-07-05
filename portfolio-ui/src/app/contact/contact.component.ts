import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, AbstractControl } from '@angular/forms';
import { ContactMessage } from '../models/contactMessage';
import { PortfolioService } from '../services/portfolio.service';
import { finalize } from 'rxjs/operators';
import { ContactMessageStatus } from '../models/contactMessageStatus';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  public emailFormGroup: FormGroup;
  public messageStatus: ContactMessageStatus;

  constructor(
    private formBuilder: FormBuilder,
    private portfolioService: PortfolioService
  ) { 
    this.initForm();
  }

  ngOnInit(): void {
    this.messageStatus = {
      isSending: false,
      isError: false,
      isSuccess: false
    }
  }

  public onEmailFormSubmit(event: any): void {
    event.preventDefault();
    if (this.emailFormGroup.invalid){ 
      for (let controlKey in this.emailFormGroup.controls)
        this.emailFormGroup.controls[controlKey].markAsTouched();
    } else {
      let message: ContactMessage = {
        subject: `${this.name.value} (${this.email.value}) sent portfolio contact message`,
        senderEmail: this.email.value,
        senderName: this.name.value,
        body: this.message.value
      };

      this.messageStatus.isSending = true;
      this.portfolioService.sendEmail(message).pipe(
        finalize(() => this.messageStatus.isSending = false)
      ).subscribe(() => {
        this.messageStatus.isError = false;
        this.messageStatus.isSuccess = true;
      }, (err) => {
        this.messageStatus.isSuccess = false;
        this.messageStatus.isError = true;
        console.error("Error sending message: ", err);
      });
    }

  }

  private initForm(): void {
    this.emailFormGroup = this.formBuilder.group({
      name: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      message: new FormControl(null, [Validators.required, Validators.minLength(5)])
    });
  }

  get name(): AbstractControl {
    return this.emailFormGroup.controls['name'];
  }
  
  get email(): AbstractControl {
    return this.emailFormGroup.controls['email'];
  }

  get message(): AbstractControl {
    return this.emailFormGroup.controls['message'];
  }

}
