import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {UserListComponent} from './components/admin/user-list/user-list.component';
import {LoginComponent} from './components/login/login.component';
import {AuthService} from "./service/auth.service";
import {RoleService} from "./service/role.service";
import {TokenStorageService} from "./service/token-storage.service";
import {UserService} from "./service/user.service";
import {UserStatusService} from "./service/user-status.service";
import {AuthInterceptor} from "./security/AuthInterceptor";
import {TicketListComponent} from './components/ticket-list/ticket-list.component';
import {CinemaListComponent} from './components/cinema-list/cinema-list.component';
import {UserCinemaListComponent} from './components/admin/user-cinema-list/user-cinema-list.component';
import {CinemaDetailsComponent} from './components/cinema-details/cinema-details.component';
import {HallListComponent} from './components/hall-list/hall-list.component';
import {HallDetailsComponent} from './components/hall-details/hall-details.component';
import {HomePageComponent} from './components/home-page/home-page.component';
import {FilmSessionDetailsComponent} from './components/film-session-details/film-session-details.component';
import {ProfileComponent} from './components/profile/profile.component';
import {CreateUserComponent} from './components/admin/create-user/create-user.component';
import {CinemaService} from "./service/cinema.service";
import {HallService} from "./service/hall.service";
import {FilmSessionService} from "./service/film-session.service";
import {TicketService} from "./service/ticket.service";
import {CreateCinemaComponent} from './components/owner/create-cinema/create-cinema.component';
import {UpdateCinemaComponent} from './components/owner/update-cinema/update-cinema.component';
import {OwnerCinemaListComponent} from './components/owner-cinema-list/owner-cinema-list.component';
import {CreateHallComponent} from './components/owner/create-hall/create-hall.component';
import {CreateFilmSessionComponent} from "./components/manager/create-film-session/create-film-session.component";
import {CreateFilmComponent} from "./components/owner/create-film/create-film.component";
import {FilmListComponent} from './components/film-list/film-list.component';
import { UpdateHallComponent } from './components/owner/update-hall/update-hall.component';
import { OwnerFilmsComponent } from './components/owner/owner-films/owner-films.component';
import { FilmStatisticsComponent } from './components/owner/film-statistics/film-statistics.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegistrationComponent,
    UserListComponent,
    LoginComponent,
    TicketListComponent,
    CinemaListComponent,
    UserCinemaListComponent,
    CinemaDetailsComponent,
    HallListComponent,
    HallDetailsComponent,
    HomePageComponent,
    FilmSessionDetailsComponent,
    ProfileComponent,
    CreateUserComponent,
    CreateCinemaComponent,
    UpdateCinemaComponent,
    OwnerCinemaListComponent,
    CreateHallComponent,
    CreateFilmSessionComponent,
    CreateFilmComponent,
    FilmListComponent,
    UpdateHallComponent,
    OwnerFilmsComponent,
    FilmStatisticsComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
  providers: [
    AuthService,
    RoleService,
    TokenStorageService,
    UserService,
    UserStatusService,
    CinemaService,
    HallService,
    FilmSessionService,
    TicketService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
