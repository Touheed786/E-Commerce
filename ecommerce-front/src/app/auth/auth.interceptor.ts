import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { UserAuthService } from "../services/user-auth.service";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { catchError } from "rxjs/operators";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    constructor(private userAuth:UserAuthService,private router:Router){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       if(req.headers.get("No-Auth")){
        return next.handle(req.clone())
       }
       const token = this.userAuth.getToken();
       console.log(token)
       console.log(`Token of Interceptor ${token}`)

       req =  this.addToken(req,token);

       return next.handle(req).pipe(
        catchError((error: HttpErrorResponse) => {
            // You can handle errors here, for example, redirect to login page on 401 Unauthorized
            if (error.status === 401) {
                this.router.navigate(['/login'])
              // Handle unauthorized error
              console.error('Unauthorized. Redirecting to login page.');
              // You can also navigate to the login page using Angular Router
            }else if(error.status === 403)
            {
                this.router.navigate(['/forbidden'])
            }
            // Propagate the error to the consumer of the observable
            return throwError("Something is Wrong");
          })
       );
    }

    private addToken(request:HttpRequest<any>,token:any){
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        })
    }
}