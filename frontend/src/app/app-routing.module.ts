import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {UserListComponent} from "./components/user-list/user-list.component";
import {LoginComponent} from "./components/login/login.component";
import {UserCinemaListComponent} from "./components/user-cinema-list/user-cinema-list.component";
import {CinemaDetailsComponent} from "./components/cinema-details/cinema-details.component";
import {HallDetailsComponent} from "./components/hall-details/hall-details.component";
import {CinemaListComponent} from "./components/cinema-list/cinema-list.component";
import {FilmSessionDetailsComponent} from "./components/film-session-details/film-session-details.component";
import {ProfileComponent} from "./components/profile/profile.component";


const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'users', component: UserListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'owner/cinemas', component: UserCinemaListComponent},
  {path: 'cinemas', component: CinemaListComponent},
  {path: 'cinema/:name', component: CinemaDetailsComponent},
  {path: 'hall/:name', component: HallDetailsComponent},
  {path: 'film-session/:id', component: FilmSessionDetailsComponent},
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
