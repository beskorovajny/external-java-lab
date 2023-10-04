import {Component, Input, OnInit} from '@angular/core';
import {CheckLoginService} from "../../service/auth/check-login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{
  @Input() isLoggedIn: boolean = false;
}
