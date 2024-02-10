import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { LoginData } from '../model/user.model';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private userService:UserService,private userAuth:UserAuthService,private router:Router){}

  loginData:LoginData = new LoginData()

  login(){
    this.userService.login(this.loginData).subscribe((data:any)=>{
      this.userAuth.setToken(data.jwtToken);
      this.userAuth.setRoles(data.user.roles);

      const role = data.user.roles[0].roleName;
      if(role == 'Admin')
      {
        this.router.navigate(['/admin'])
      }else{
        this.router.navigate(['/user'])
      }
     
    },(err)=>{
      console.log(err)
    })
    console.log("loginForm")
  }

}
