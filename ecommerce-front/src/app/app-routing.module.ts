import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { authGuard } from './auth/auth.guard';
import { AddProductComponent } from './add-product/add-product.component';

const routes: Routes = [
  {
    path:"",
    component:HomeComponent
  },
  {
    path:"admin",
    component:AdminComponent,
    canActivate:[authGuard],
    data:{roles:['Admin']}
  },
  {
    path:"user",
    component:UserComponent,
    canActivate:[authGuard],
    data:{roles:['User']}
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"forbidden",
    component:ForbiddenComponent
  },
  {
    path:"addProduct",
    component:AddProductComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
