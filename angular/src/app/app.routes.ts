import { Routes } from '@angular/router';
import { LoginFormComponent } from './User/login/login/login-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {authGuard} from './User/auth/auth.guard';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginFormComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard]},
  // { path: 'register', component: RegisterFormComponent },
  // { path: '', component }

];
