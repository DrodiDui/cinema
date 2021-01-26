import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCinemaListComponent } from './user-cinema-list.component';

describe('UserCinemaListComponent', () => {
  let component: UserCinemaListComponent;
  let fixture: ComponentFixture<UserCinemaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCinemaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCinemaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
