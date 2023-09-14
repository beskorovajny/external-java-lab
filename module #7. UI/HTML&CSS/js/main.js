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


/**
 * Token manipulation buttons
 */
const tokenShow = document.getElementById('tokenShowBtn')
tokenShow.addEventListener('click', showToken, false)

function showToken() {
    const token = localStorage.getItem('token')
    alert(token)
}

const resetToken = document.getElementById('tokenRemoveBtn')

resetToken.addEventListener('click', function () {
    const token = localStorage.getItem('token')
    if (token.length > 0) {
        const homePage = 'http://localhost:63342/external-java-lab/module%20%237.%20UI/HTML&CSS/home.html?_ijt=qlphio5aveel866866rp8d9kov&_ij_reload=RELOAD_ON_SAVE'
        logout(token)
        window.location.href = homePage
    }
    localStorage.removeItem('token')
})

/**
 * Logout
 */
async function logout(token) {
    try {
        const url = `http://localhost:8080/api/auth/logout`

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '.concat(token)
            },
        };

        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            alert('Wrong token')
        }
    } catch (error) {
        console.error('Failure in logout request:', error.message, error.stack)
    }
}

