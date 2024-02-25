import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import PATH_OF_API from './url';
import { LoginData } from '../model/user.model';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  requestHeader = new HttpHeaders({
    "No-Auth":"true"
  })

  constructor(private http:HttpClient,private userAuth:UserAuthService) { }

  public login(loginData:LoginData)
  {
    return this.http.post(`${PATH_OF_API}/authenticate`,loginData,{headers:this.requestHeader});
  }

  public forUser()
  {
    return this.http.get(`${PATH_OF_API}/user/forUser`,{
      responseType: 'text',
    });
  }

  public forAdmin()
  {
    return this.http.get(`${PATH_OF_API}/user/forAdmin`,{
      responseType: 'text',
    });
  }
  public forTest()
  {
    return this.http.get(`${PATH_OF_API}/user/test`,{
      responseType: 'text',
    });
  }

  public roleMatch(allowedRole:any):boolean 
  {

    let isMatch = false;
    const userRoles:any = JSON.parse(this.userAuth.getRoles());
    if(userRoles!=null && userRoles)
    {
      for(let i =0;i<userRoles.length;i++)
      {
        for(let j = 0;j<allowedRole.length;j++)
        {
          if(userRoles[i].roleName === allowedRole[j]){
            isMatch = true;
            return isMatch;
          }else{
            return isMatch;
          }
        }
      }
    }
    return isMatch;

  }  
   public isAdmin(){
    const role:any = JSON.parse(this.userAuth.getRoles());
    if(role != null){
      
      console.log("this is the role",role)
      console.log(role[0].roleName)
      return role[0].roleName == "Admin";
    }
    return false;
   }

   public isUser(){
    
    const role:any[] = JSON.parse(this.userAuth.getRoles());
    if(role != null){

      console.log(role[0].roleName)
      return role[0].roleName == "User";
    }
    return false;
   }
}
