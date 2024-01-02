import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormQuizComponent } from './form-quiz.component';

describe('FormQuizComponent', () => {
  let component: FormQuizComponent;
  let fixture: ComponentFixture<FormQuizComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormQuizComponent]
    });
    fixture = TestBed.createComponent(FormQuizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
