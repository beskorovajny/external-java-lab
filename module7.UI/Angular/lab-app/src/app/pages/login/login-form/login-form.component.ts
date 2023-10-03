import {Component, EventEmitter, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  loginForm: FormGroup
  @Output() loginData = new EventEmitter<FormGroup>

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.minLength(8)]],
      password: ['', [Validators.required,
        Validators.minLength(8), Validators.maxLength(64)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.loginData.emit(this.loginForm)
    }
  }
}
