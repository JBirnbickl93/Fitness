import { UserModel } from './user.model';

describe('UserModel', () => {
  it('should create an instance', () => {
    expect(new UserModel('John', '12345')).toBeTruthy();
  });
});
