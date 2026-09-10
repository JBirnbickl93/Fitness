import {Component} from '@angular/core';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {Router} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../auth/auth.service';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-login-component',
  imports: [
    MatFormField,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    NgOptimizedImage
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {

  constructor(private router: Router,
              private authService: AuthService,) {
  }

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  })

  loginError: string = '';

  onSubmit(): void {
    if (this.loginForm.invalid) {
      return;
    }

    const email = this.loginForm.controls.email.value;
    const password = this.loginForm.controls.password.value;

    if (!email || !password) {
      return;
    }
    this.authService.login(email, password)
      .subscribe({
      next: response => {
        this.authService.saveToken(response.token);
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.loginError = 'Email or password is incorrect.';
      }
    })
  }
}
