import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {UserListComponent} from "./components/user-list/user-list.component";
import {LoginComponent} from "./components/login/login.component";
import {UserCinemaListComponent} from "./components/user-cinema-list/user-cinema-list.component";


const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'users', component: UserListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'owner/cinemas', component: UserCinemaListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
