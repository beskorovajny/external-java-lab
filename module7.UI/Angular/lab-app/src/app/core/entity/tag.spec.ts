import { Tag } from './tag';

describe('Tag', () => {
  it('should create an instance', () => {
    expect(new Tag(1, 'name')).toBeTruthy();
  });
});
