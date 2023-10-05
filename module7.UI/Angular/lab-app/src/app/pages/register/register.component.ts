import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../../service/auth/auth.service";
import {Jwt} from "../../core/entity/jwt/jwt";
import {Router} from "@angular/router";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    signUpForm: FormGroup;
    jwt!: Jwt;

    constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
        this.signUpForm = this.fb.group({
            firstName: ['', [Validators.required,Validators.minLength(3), Validators.maxLength(64)]],
            password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(64)]],
            email: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(64)]],
            lastName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(64)]],
            repeatPassword: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(64)]],
        });
    }

    onSubmit() {
        if (this.signUpForm.valid) {
           this.register()
            console.log('Register form submitted:', this.signUpForm.value);
        }
    }

    register() {
        const credentials = {
            firstName: this.signUpForm.value.firstName,
            password: this.signUpForm.value.password,
            email: this.signUpForm.value.email,
            lastName: this.signUpForm.value.lastName,
        };

        this.authService.register(credentials)
            .subscribe(
                (jwt) => {
                    this.jwt = jwt;

                    const accessToken = jwt.accessToken;
                    const userEmail = jwt.userEmail;
                    const role = jwt.userRole;

                    window.localStorage.setItem('role', role);
                    window.localStorage.setItem('user', userEmail);
                    window.localStorage.setItem(userEmail, accessToken)

                    this.router.navigate(['/home']);
                },
                error => {
                    console.error('Registration failed', error);
                }
            );
    }
}
