import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerCinemaListComponent } from './owner-cinema-list.component';

describe('OwnerCinemaListComponent', () => {
  let component: OwnerCinemaListComponent;
  let fixture: ComponentFixture<OwnerCinemaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerCinemaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerCinemaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
