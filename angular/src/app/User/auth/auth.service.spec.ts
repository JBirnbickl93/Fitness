import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth.service';
import {provideHttpClient} from '@angular/common/http';
import {HttpTestingController, provideHttpClientTesting} from '@angular/common/http/testing';

describe('AuthService', () => {

  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(),
        provideHttpClientTesting()]
    });
    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should save and return a token', () => {
    service.saveToken('abc123');
    expect(service.getToken()).toBe('abc123');
  });

  it('should return a token after login', () => {
    service.login('test@test.de', 'test').subscribe((response) => {
      expect(response.token).toBe('abc123');
    });

    const request = httpMock.expectOne('/api/auth/login');

    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual({
      email: 'test@test.de',
      password: 'test'
    });
    request.flush({
      token: 'abc123'
    });
  });
});
