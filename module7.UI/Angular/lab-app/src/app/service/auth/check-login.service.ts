import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CheckLoginService {

  checkLogin(): boolean {
    const userData = window.localStorage.getItem('user');
    return userData !== null && userData.length > 0;
  }
}
