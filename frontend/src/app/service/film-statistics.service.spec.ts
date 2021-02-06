import { TestBed } from '@angular/core/testing';

import { FilmStatisticsService } from './film-statistics.service';

describe('FilmStatisticsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FilmStatisticsService = TestBed.get(FilmStatisticsService);
    expect(service).toBeTruthy();
  });
});
