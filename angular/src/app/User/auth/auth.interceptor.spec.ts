import { TestBed } from '@angular/core/testing';
import { HttpInterceptorFn, provideHttpClient, HttpRequest, HttpResponse, HttpHandlerFn} from '@angular/common/http';
import { authInterceptor } from './auth.interceptor';
import {AuthService} from './auth.service';
import { provideHttpClientTesting} from '@angular/common/http/testing';
import {of} from 'rxjs';

describe('authInterceptor', () => {
  const interceptor: HttpInterceptorFn = (req, next) =>
    TestBed.runInInjectionContext(() => authInterceptor(req, next));

  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(),
      provideHttpClientTesting()]
    });
    authService = TestBed.inject(AuthService);
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
