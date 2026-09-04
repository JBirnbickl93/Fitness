import { TestBed } from '@angular/core/testing';
import {
  HttpInterceptorFn,
  provideHttpClient,
  HttpRequest,
  HttpResponse,
  HttpHandlerFn,
  HttpClient, withInterceptors
} from '@angular/common/http';
import { authInterceptor } from './auth.interceptor';
import {AuthService} from './auth.service';
import {HttpTestingController, provideHttpClientTesting} from '@angular/common/http/testing';
import {of} from 'rxjs';

describe('authInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => authInterceptor(req, next));

  let authService: AuthService;
  let httpMock: HttpTestingController
  let http: HttpClient;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(withInterceptors([authInterceptor])),
      provideHttpClientTesting()]
    });
    authService = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
    http = TestBed.inject(HttpClient);
  });

  it('should not add an Authorization header to auth requests', () => {
    http.post('/api/auth/login', {}).subscribe();
    const req = httpMock.expectOne('/api/auth/login');
    expect(req.request.headers.has('Authorization')).toBeFalse();
    req.flush({});
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
