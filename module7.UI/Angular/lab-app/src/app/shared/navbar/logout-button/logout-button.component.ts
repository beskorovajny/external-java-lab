import {Component} from '@angular/core';
import {AuthService} from "../../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout-button',
  templateUrl: './logout-button.component.html',
  styleUrls: ['./logout-button.component.css']
})
export class LogoutButtonComponent {

  constructor(private authService: AuthService,
              private router: Router,) {
  }

  onLogout() {
    this.authService.logout().subscribe(result => {
        if (result) {
          console.log("Logout!")
        }
      },
      (error) => {
        console.error('Logout failed', error);
      });
    this.router.navigate(['/login'])
  }
}
