import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFilmSessionComponent } from './create-film-session.component';

describe('CreateFilmSessionComponent', () => {
  let component: CreateFilmSessionComponent;
  let fixture: ComponentFixture<CreateFilmSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFilmSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFilmSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
