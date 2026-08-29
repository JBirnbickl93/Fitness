import {Component} from '@angular/core';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {Router} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../auth/auth.service';

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
              private authService: AuthService,) {
  }

  email: string = '';
  password: string = '';
  loginError: string = '';

  protected login(): void {
    this.authService.login(this.email, this.password)
      .subscribe({
      next: response => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/dashboard']);
      },
      error: error => {
        this.loginError = 'Email or password is incorrect.';
      }
    })
  }
}
