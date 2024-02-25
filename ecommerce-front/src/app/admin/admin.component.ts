import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent {

  constructor(private userService:UserService){}

  message:string;
  ngOnInit(){
    this.forAdmin()
  }

  forAdmin(){
    this.userService.forAdmin().subscribe((data:any)=>{
      this.message = data;
    })
  }

  forTest(){
    this.userService.forTest().subscribe((data:any)=>{
      console.log(data)
    })
  }
}
