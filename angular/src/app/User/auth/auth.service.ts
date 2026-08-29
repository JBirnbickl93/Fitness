import { Injectable } from '@angular/core';
import {HttpClient, HttpInterceptor} from '@angular/common/http';
import {shareReplay} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient, private httpInterceptor: HttpInterceptor) {
  }

  login(email: string, password: string) {
    return this.http.post<LoginResponse>(
      `api/auth/login`,
      {email, password})
      .pipe(shareReplay(1))
  }

  saveToken(token: string) {
    localStorage.setItem('token', token)
  }

  getToken():string | null {
    return localStorage.getItem('token')
  }

  isAuthenticated(token: string) {
    return !!localStorage.getItem('token');
  }

}

interface LoginResponse {
  token: string;
}
