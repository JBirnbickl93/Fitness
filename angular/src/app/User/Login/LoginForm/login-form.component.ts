import {Component} from '@angular/core';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {Router} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-login-component',
  imports: [
    MatFormField,
    MatInputModule,
    MatButtonModule,
    FormsModule
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {

  constructor(private router: Router,
              private http: HttpClient) {
  }

  email: string = '';
  password: string = '';

  protected login(): void {
    this.http.post<{ token: string }>(
      '/api/auth/login',
      {
        email: this.email,
        password: this.password
      }
    ).subscribe({
      next: response => {
        console.log('Login successful');
        console.log(response.token);
        this.router.navigate(['/dashboard']);
      },
      error: error => {
        console.log('Login failed.', error);
      }
    })

  }
}
  // TODO: EventEmitter for login action
