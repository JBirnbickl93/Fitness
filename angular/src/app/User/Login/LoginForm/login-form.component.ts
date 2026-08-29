import {Component, Input} from '@angular/core';
import {MatFormField, MatInputModule} from '@angular/material/input';
import {Router} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';

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

  constructor(private router: Router) { }
  username: string = " ";
  password: string = " ";

  protected login(): void {
    console.log(this.username);
    console.log(this.password);
    this.router.navigate(['/dashboard']);
  }
  // TODO: EventEmitter for login action
}
