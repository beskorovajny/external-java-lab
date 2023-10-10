import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const expectedRole = route.data['expectedRole'];
  const userRole = window.localStorage.getItem('role')

  if (userRole === expectedRole) {
    return true;
  } else {
    alert('Access denied!!!')
    new Router().navigate(['/home']);
    return false;
  }
};
