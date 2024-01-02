import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KoatchComponent } from './koatch.component';

describe('KoatchComponent', () => {
  let component: KoatchComponent;
  let fixture: ComponentFixture<KoatchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KoatchComponent]
    });
    fixture = TestBed.createComponent(KoatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
