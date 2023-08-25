/* Search bar script */
let items = document.getElementsByName('item');
let selectedItem = document.getElementById('selected-item');
let dropdown = document.getElementById('dropdown');

items.forEach(item => {
  item.addEventListener('change', () => {
    if (item.checked) {
      selectedItem.innerHTML = item.value;
      dropdown.open = false;
    }
  });
});


/* Search bar script */
let itemsCat = document.getElementsByName('item-cat');
let selectedItemCat = document.getElementById('selected-item-category');
let dropdownCat = document.getElementById('dropdown-category');

itemsCat.forEach(item => {
  item.addEventListener('change', () => {
    if (item.checked) {
      selectedItemCat.innerHTML = item.value;
      dropdownCat.open = false;
    }
  });
});
