import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmStatisticsComponent } from './film-statistics.component';

describe('FilmStatisticsComponent', () => {
  let component: FilmStatisticsComponent;
  let fixture: ComponentFixture<FilmStatisticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmStatisticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
