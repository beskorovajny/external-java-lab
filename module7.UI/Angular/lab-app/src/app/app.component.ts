import {Component, OnInit} from '@angular/core';
import {CheckLoginService} from "./service/auth/check-login.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'lab-app';

  isLoggedIn: boolean = false;

  constructor(private checkLoginService: CheckLoginService, private router: Router) {
  }

  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.checkLogin();
      }
    });
  }

  checkLogin() {
    this.isLoggedIn = this.checkLoginService.checkLogin();
  }

}
