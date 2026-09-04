import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn, provideHttpClient, HttpRequest, HttpResponse, HttpHandlerFn} from '@angular/common/http';
import { authInterceptor } from './auth.interceptor';
import {AuthService} from './auth.service';
import {HttpTestingController, provideHttpClientTesting} from '@angular/common/http/testing';
import {of} from 'rxjs';

describe('authInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => authInterceptor(req, next));

  let authService: AuthService;
  let httpMock: HttpTestingController

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(),
      provideHttpClientTesting()]
    });
    authService = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should forward the user to auth-paths', () => {
    authService.login('test@test.de', 'password');
    const req = httpMock.expectOne('/api/auth/login');
    expect(req.request.headers.has('Authorization')).toBeFalse();
    req.flush({token: 'fake-token'});
  });



  it('should add an Authorization header if a token is present', () => {
    spyOn(authService, 'getToken').and.returnValue('abc123');
    const req = new HttpRequest(
      'GET',
      '/api/workouts/entries/'
    );
    const next: HttpHandlerFn = request => {
      expect(request.headers.get('Authorization'))
        .toBe('Bearer abc123');
      return of(new HttpResponse());
    };
    interceptor(req, next).subscribe();
  });
});
