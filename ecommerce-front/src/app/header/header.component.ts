import { Component } from '@angular/core';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(public userAuth:UserAuthService,private router:Router,public userService:UserService){}

  public isLoggedIn()
  {
    return this.userAuth.isLoggedIn();
  }

  public logout()
  {
    this.userAuth.clear();
    this.router.navigate(['/'])
  }

}
