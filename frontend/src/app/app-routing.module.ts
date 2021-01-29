import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from "./components/registration/registration.component";
import {UserListComponent} from "./components/admin/user-list/user-list.component";
import {LoginComponent} from "./components/login/login.component";
import {UserCinemaListComponent} from "./components/admin/user-cinema-list/user-cinema-list.component";
import {CinemaDetailsComponent} from "./components/cinema-details/cinema-details.component";
import {HallDetailsComponent} from "./components/hall-details/hall-details.component";
import {CinemaListComponent} from "./components/cinema-list/cinema-list.component";
import {FilmSessionDetailsComponent} from "./components/film-session-details/film-session-details.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {CreateUserComponent} from "./components/admin/create-user/create-user.component";
import {HomePageComponent} from "./components/home-page/home-page.component";
import {CreateCinemaComponent} from "./components/owner/create-cinema/create-cinema.component";
import {CreateHallComponent} from "./components/owner/create-hall/create-hall.component";
import {CreateFilmSessionComponent} from "./components/create-film-session/create-film-session.component";
import {FilmListComponent} from "./components/film-list/film-list.component";
import {CreateFilmComponent} from "./components/owner/create-film/create-film.component";
import {UpdateFilmSessionComponent} from "./components/manager/update-film-session/update-film-session.component";
import {UpdateCinemaComponent} from "./components/owner/update-cinema/update-cinema.component";
import {UpdateHallComponent} from "./components/owner/update-hall/update-hall.component";
import {OwnerFilmsComponent} from "./components/owner/owner-films/owner-films.component";


const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'admin/users', component: UserListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'owner/cinemas', component: UserCinemaListComponent},
  {path: 'cinemas', component: CinemaListComponent},
  {path: 'cinema/:cinema-name', component: CinemaDetailsComponent},
  {path: 'cinema/:cinema-name/hall/:hall-name', component: HallDetailsComponent},
  {path: 'film-session/:id', component: FilmSessionDetailsComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'admin/users/create', component: CreateUserComponent},
  {path: 'owner/cinemas/create', component: CreateCinemaComponent},
  {path: 'owner/cinema/:id/create/manager', component: CreateUserComponent},
  {path: 'owner/cinema/:id/create/hall', component: CreateHallComponent},
  {path: 'owner/cinema/:id/create/hall/:hall-id/create/filmSession', component: CreateFilmSessionComponent},
  {path: 'owner/cinema/:name/update', component: UpdateCinemaComponent},
  {path: 'owner/cinema/:cinema-name/halls', component: CinemaDetailsComponent},
  {path: 'owner/cinema/:cinema-name/hall/:hall-name/update', component: UpdateHallComponent},
  {path: 'owner/films', component: OwnerFilmsComponent},
  {path: 'owner/film/create', component: CreateFilmComponent},
  {path: 'film-session/update/:id', component: UpdateFilmSessionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
