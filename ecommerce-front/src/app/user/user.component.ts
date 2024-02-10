import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent {

  message:string
  constructor(private userService:UserService){}

  ngOnInit(){
    this.forUser()
  }

  public forUser()
  {
    this.userService.forUser().subscribe((data:any)=>{
      console.log(data)
      this.message = data;
    },(error)=>{
      console.log(error)
    })
  }

}
