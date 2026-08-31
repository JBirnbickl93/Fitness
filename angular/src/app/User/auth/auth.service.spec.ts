import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {LoginFormComponent} from '../login/login/login-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';

describe('AuthService', () => {
  let service: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [LoginFormComponent],
      providers: [provideHttpClient(),
      provideHttpClientTesting()]
    });
    service = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return a token after login', () => {
    expect(localStorage.getItem('token')).toBeTruthy();
  })
});
