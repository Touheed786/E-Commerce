import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { LoginData } from '../model/user.model';
import { UserAuthService } from '../services/user-auth.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private userService:UserService,private userAuth:UserAuthService,private router:Router){}

  loginData:LoginData = new LoginData()
  loginFailed:string;

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
      const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.onmouseenter = Swal.stopTimer;
          toast.onmouseleave = Swal.resumeTimer;
        }
      });
      Toast.fire({
        icon: "success",
        title: "Signed in successfully"
      });
     
    },(err)=>{
      console.log(err)
      this.loginFailed = "Username or Passwrod is invalid"
    })
    console.log("loginForm")
  }

}
