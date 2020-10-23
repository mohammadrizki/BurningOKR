import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectiveFormContactComponent } from './objective-form-contact.component';

describe('ObjectiveFormContactComponent', () => {
  let component: ObjectiveFormContactComponent;
  let fixture: ComponentFixture<ObjectiveFormContactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectiveFormContactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectiveFormContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
