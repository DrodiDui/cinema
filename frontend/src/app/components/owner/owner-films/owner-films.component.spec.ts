import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerFilmsComponent } from './owner-films.component';

describe('OwnerFilmsComponent', () => {
  let component: OwnerFilmsComponent;
  let fixture: ComponentFixture<OwnerFilmsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnerFilmsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnerFilmsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
