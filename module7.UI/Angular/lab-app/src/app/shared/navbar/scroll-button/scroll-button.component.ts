import {Component, HostListener} from '@angular/core';

@Component({
  selector: 'app-scroll-button',
  templateUrl: './scroll-button.component.html',
  styleUrls: ['./scroll-button.component.css']
})
export class ScrollButtonComponent {
  showRestoreBtn: boolean = false;
  lastPosition: number = 0;
  scrollText: string = 'Top';


  @HostListener('window:scroll', [])
  onScroll(): void {
    this.showRestoreBtn = (window.scrollY > 70 && this.scrollText === 'Top') ||
      (window.scrollY < 70 && this.lastPosition > 0 && this.scrollText === 'Back');

  }

  toggleScroll(): void {
    if (this.scrollText === 'Back') {
      this.restoreScroll();
      this.scrollText = 'Top';
    } else {
      this.scrollToTop();
      this.scrollText = 'Back';
    }
  }

  scrollToTop(): void {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
    this.lastPosition = window.scrollY;
  }


  restoreScroll(): void {
    window.scrollTo({
      top: this.lastPosition,
      behavior: 'smooth'
    });
    this.lastPosition = 0;
  }

}
