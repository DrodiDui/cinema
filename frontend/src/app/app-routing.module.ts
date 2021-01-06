import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {UserListComponent} from "./components/user-list/user-list.component";


const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'users', component: UserListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
