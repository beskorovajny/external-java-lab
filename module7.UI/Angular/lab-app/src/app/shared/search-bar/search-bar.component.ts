import {Component} from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {

}

/*let select = document.getElementById("select")!;
let categories = document.getElementById("categories")!;
let selectedText = document.getElementById("selectedText")!;
let options = document.getElementsByClassName("search-item");

select.addEventListener('click', function () {
  categories.classList.toggle("open")
})

for (let option of options) {
  if (option instanceof HTMLElement) {
    option.onclick = function () {
      selectedText.innerHTML = option.innerHTML;
    }
  }
}*/
