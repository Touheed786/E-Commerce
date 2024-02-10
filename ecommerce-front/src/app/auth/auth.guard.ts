import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserAuthService } from '../services/user-auth.service';
import { UserService } from '../services/user.service';

export const authGuard: CanActivateFn = (route, state) => {

  const router  = inject(Router);
  const userAuth = inject(UserAuthService);
  const userService = inject(UserService);

  if(userAuth.getToken !== null){
    const role = route.data["roles"] as Array<String>;
    if(role)
    {
      const match = userService.roleMatch(role);
      if(match)
      {
        return true;
      }else{
        router.navigate(['/forbidden'])
        return false;
      }
    }
  }
  router.navigate(['/login'])
  return false;
};
