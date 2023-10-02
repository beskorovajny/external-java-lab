import { Certificate } from './certificate';

describe('Certificate', () => {
  it('should create an instance', () => {
    expect(new Certificate(1, 'name', 'desc', 1, 22.5, new Date()))
      .toBeTruthy();
  });
});
