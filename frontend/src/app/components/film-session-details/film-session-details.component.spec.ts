import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilmSessionDetailsComponent } from './film-session-details.component';

describe('FilmSessionDetailsComponent', () => {
  let component: FilmSessionDetailsComponent;
  let fixture: ComponentFixture<FilmSessionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilmSessionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilmSessionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
