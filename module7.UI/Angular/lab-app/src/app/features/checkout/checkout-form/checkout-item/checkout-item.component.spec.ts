import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckoutItemComponent } from './checkout-item.component';

describe('CheckoutItemComponent', () => {
  let component: CheckoutItemComponent;
  let fixture: ComponentFixture<CheckoutItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CheckoutItemComponent]
    });
    fixture = TestBed.createComponent(CheckoutItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
