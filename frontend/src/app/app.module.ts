import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { RegistrationComponent } from './components/registration/registration.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { UserListComponent } from './components/user-list/user-list.component';
import { LoginComponent } from './components/login/login.component';
import {AuthService} from "./service/auth.service";
import {RoleService} from "./service/role.service";
import {TokenStorageService} from "./service/token-storage.service";
import {UserService} from "./service/user.service";
import {UserStatusService} from "./service/user-status.service";
import {AuthInterceptor} from "./security/AuthInterceptor";
import { TicketListComponent } from './components/ticket-list/ticket-list.component';
import { CinemaListComponent } from './components/cinema-list/cinema-list.component';
import { UserCinemaListComponent } from './components/user-cinema-list/user-cinema-list.component';
import { CinemaDetailsComponent } from './components/cinema-details/cinema-details.component';
import { HallListComponent } from './components/hall-list/hall-list.component';
import { HallDetailsComponent } from './components/hall-details/hall-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FilmSessionDetailsComponent } from './components/film-session-details/film-session-details.component';
import { ProfileComponent } from './components/profile/profile.component';

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
    ProfileComponent
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
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
