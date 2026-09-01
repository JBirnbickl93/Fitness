import { Injectable } from '@angular/core';
import {HttpClient, HttpInterceptor} from '@angular/common/http';
import {shareReplay} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth'
  constructor(private http: HttpClient) {
  }

  login(email: string, password: string) {
    return this.http.post<LoginResponse>(
      this.apiUrl + `/login`,
      {email, password})
      .pipe(shareReplay(1))
  }

  saveToken(token: string) {
    localStorage.setItem('token', token)
  }

  getToken():string | null {
    return localStorage.getItem('token')
  }

  // Prüft bisher nur, ob ein Token im Local Storage ist, nicht, ob er gültig ist (JWT-Filter Backend)
  isAuthenticated() : boolean {
    return !!localStorage.getItem('token');
  }

}

interface LoginResponse {
  token: string;
}
