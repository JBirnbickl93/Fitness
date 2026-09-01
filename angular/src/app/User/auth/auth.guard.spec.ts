import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authGuard } from './auth.guard';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {Router} from '@angular/router';
import { AuthService} from './auth.service';

describe('authGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) =>
      TestBed.runInInjectionContext(() => authGuard(...guardParameters));

  let authService: AuthService;
  let router: Router;
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(),
      provideHttpClientTesting()]
    });
    authService = TestBed.inject(AuthService);
    router = TestBed.inject(Router);
  });

  it('user should be authenticated and authguard will activate route', () => {
    spyOn(authService, "isAuthenticated").and.returnValue(true);

    const result = executeGuard({} as any, {} as any)

    expect(result).toBeTrue();
  });

  it('user is not authenticated and route should redirect', () => {
    spyOn(authService, "isAuthenticated").and.returnValue(false);

    const result = executeGuard({} as any, {} as any)

    expect(result).toEqual(
      router.createUrlTree(['/login'])
    );
  });
});
