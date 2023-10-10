import {Component} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {AuthService} from "../../service/auth/auth.service";
import {Jwt} from "../../core/entity/jwt/jwt";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;
  jwt!: Jwt;

  constructor(private authService: AuthService, private router: Router) {
  }

  handleLoginInfoFromLoginForm(loginForm: FormGroup) {
    this.loginForm = loginForm;
    this.login();
  }

  login() {
    if (this.loginForm !== undefined) {
      const credentials = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };

      this.authService.login(credentials)
        .subscribe((response) => {
            this.jwt = response;

            console.log('Login successful', this.jwt);

            const accessToken = this.jwt.accessToken;
            const userEmail = this.jwt.userEmail;
            const role = this.jwt.userRole;

            window.localStorage.setItem('role', role);
            window.localStorage.setItem('user', userEmail);
            window.localStorage.setItem(userEmail, accessToken)

            this.router.navigate(['/home']);
          },
          error => {
            console.error('Login failed', error);
          }
        );
    }
  }
}
