import { TestBed } from '@angular/core/testing';

import { FilmSessionService } from './film-session.service';

describe('FilmSessionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FilmSessionService = TestBed.get(FilmSessionService);
    expect(service).toBeTruthy();
  });
});
