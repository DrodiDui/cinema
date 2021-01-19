import { TestBed } from '@angular/core/testing';

import { CinemaStatusService } from './cinema-status.service';

describe('CinemaStatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CinemaStatusService = TestBed.get(CinemaStatusService);
    expect(service).toBeTruthy();
  });
});
