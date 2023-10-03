import {Component} from '@angular/core';
import {AuthService} from "../../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private authService: AuthService, private router: Router) {
  }

  onLogout() {
    this.authService.logout().subscribe((result) => {
      if (result) {
        console.log("Logout!")
          alert('Logout');
        const userEmail = window.localStorage.getItem('user');
        window.localStorage.removeItem('user');
        if (userEmail) {
          window.localStorage.removeItem(userEmail);
        }
        this.router.navigate(['/login'])
      }
    },
      (error) => {
        console.error('Logout failed', error);
        alert('Logout failed')
      });
  }
}
