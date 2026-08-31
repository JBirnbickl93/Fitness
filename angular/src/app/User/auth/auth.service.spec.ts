import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {LoginFormComponent} from '../login/login/login-form.component';
import {provideHttpClient} from '@angular/common/http';
import {HttpTestingController, provideHttpClientTesting} from '@angular/common/http/testing';

describe('AuthService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [LoginFormComponent],
      providers: [provideHttpClient(),
        provideHttpClientTesting()]
    });
    service = TestBed.inject(AuthService);
  });

  let service: AuthService = TestBed.inject(AuthService);

  service.saveToken('abc123');
  expect(service.getToken()).toBe('abc123');

  service.login('test@test.de', 'test').subscribe((response) => {
    expect(response.token).toBe('abc123');
  });

  const httpMock = TestBed.inject(HttpTestingController);
  const request = httpMock.expectOne('/api/auth/login');

  request.flush({
    token: 'abc123'
  });

  expect(request.request.method).toBe('POST');
  expect(request.request.body).toEqual({
    email: 'test@test.de',
    password: 'test'
  });

})
