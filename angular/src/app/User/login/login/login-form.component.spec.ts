import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginFormComponent } from './login-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';

describe('LoginFormComponent', () => {
  let component: LoginFormComponent;
  let fixture: ComponentFixture<LoginFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginFormComponent],
      providers: [provideHttpClient(),
        provideHttpClientTesting()]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be invalid when email and password are empty', () => {
    expect(component.loginForm.invalid).toBeTrue();
  });

  it('should reject an invalid email', () => {
    component.loginForm.controls.email.setValue('abc');
    component.loginForm.controls.password.setValue('test');

    expect(component.loginForm.controls.email.hasError('email')).toBeTrue();
  });

  it('should be valid with valid email and password', () => {
    component.loginForm.controls.email.setValue('test@test.de');
    component.loginForm.controls.password.setValue('test');

    expect(component.loginForm.valid).toBeTrue();
  });

});
