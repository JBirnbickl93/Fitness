import { Routes } from '@angular/router';
import { LoginFormComponent } from './User/Login/LoginForm/login-form.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  { path: 'login', component: LoginFormComponent },
  { path: 'dashboard', component: DashboardComponent },
];
