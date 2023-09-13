'use strict';

/**
 * Scroll to top and restore position functionality
 *
 */

const scrollTopBtn = document.getElementById("scrollToTopBtn");
const restoreBtn = document.getElementById("restoreBtn");
let lastPosition = 0;
let selectedOption = document.getElementById('selected-item');

function isScrolledToBottom() {
    const windowHeight = window.innerHeight;
    const scrollY = window.scrollY || window.scrollX;
    const documentHeight = document.documentElement.scrollHeight;

    // You can adjust the threshold (e.g., 0.9) as needed
    return windowHeight + scrollY >= documentHeight * 0.9;
}


let nextPage = 0;

function onScrollToBottom(userInput) {
    alert(nextPage)
    const inputSize = userInput.length
    if (selectedOption.textContent === 'Description' && inputSize > 0) {
        fillCardsWithCertificatesByDescription(userInput, ++nextPage);
    } else if (selectedOption.textContent === 'Name' && inputSize > 0) {
        fillCardsWithCertificatesByName(userInput, ++nextPage);
    } else if (selectedOption.textContent === 'Tags' && inputSize > 0) {
        fillCardsWithCertificatesByTags(userInput,++nextPage);
    } else {
        fillCardsWithAllCertificates(++nextPage);
    }
}

window.addEventListener("scroll", () => {
    const userInput = document.getElementById("search-input").value;
    if (isScrolledToBottom()) {
        onScrollToBottom(userInput)
    }


    if (document.documentElement.scrollTop > 70) {
        scrollTopBtn.style.display = "block";
    } else {
        scrollTopBtn.style.display = "none";
    }
});
scrollTopBtn.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
    lastPosition = window.scrollY;
});

restoreBtn.addEventListener("click", () => {
    window.scrollTo({
        top: lastPosition,
        behavior: "smooth"
    });
    lastPosition = 0;
});


scrollTopBtn.onclick = function () {
    restoreBtn.style.display = "block";
}

restoreBtn.onclick = function () {
    restoreBtn.style.display = "none";
    lastPosition = 0;
}


let defaultPage = 0;
let defaultSize = 20;

/**
 * This function retrieves all GiftCertificates from endpoint
 * @param {number} page
 * @param {number} size
 * @return {any[]}
 */

async function getAllCertificates(page, size) {
    try {
        const url = `http://localhost:8080/api/certificates/find-all?page=${page}&size=${size}`;
        const response = await fetch(url);

        return response.json();
    } catch (error) {
        console.error('Failure in getAllCertificates:', error.message, error.stack)
        return []
    }
}

/**
 * This function retrieves all GiftCertificates by Name from endpoint
 * @param {string} name
 * @param {number} page
 * @param {number} size
 */
async function getCertificatesByName(name, page, size) {
    try {
        const url = `http://localhost:8080/api/certificates/find?name=${name}&page=${page}&size=${size}`
        const response = await fetch(url);

        return response.json();
    } catch (error) {
        console.error('Failure in getCertificatesByName:', error.message, error.stack)
        return []
    }
}


/**
 * This function retrieves all GiftCertificates by Description from endpoint
 * @param {string} description
 * @param {number} page
 * @param {number} size
 */
async function getCertificatesByDescription(description, page, size) {
    try {
        const url = `http://localhost:8080/api/certificates/find-all-with-params?tagName=&name=&description=${description}&sortByName=&sortByDate=DESC&page=${page}&size=${size}`
        const response = await fetch(url);

        return response.json();
    } catch (error) {
        console.error('Failure in getCertificatesByDescription:', error.message, error.stack)
        return []
    }
}


/**
 * This function retrieves all GiftCertificates by Tags from endpoint
 * @param {string} tags
 * @param {number} page
 * @param {number} size
 */
async function getCertificatesByTags(tags, page, size) {
    try {
        const url = `http://localhost:8080/api/certificates/find-by-tags?page=${page}&size=${size}`
        const tagsArray = tags.split(' ')
        const requestBody = JSON.stringify(tagsArray)
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: requestBody, // Set the JSON string as the request body
        };
        console.log(JSON.stringify(requestOptions))
        const response = await fetch(url, requestOptions);

        return response.json();
    } catch (error) {
        console.error('Failure in getCertificatesByTags:', error.message, error.stack)
        return []
    }
}


/**
 * @param {number} page
 * @return {Promise<void>}
 */
async function fillCardsWithAllCertificates(page) {
    const data = await getAllCertificates(Number.parseInt(page), defaultSize);
    const coupons = data._embedded.giftCertificateModelList;
   /* sortByCreationDate(coupons);*/
    coupons.forEach((coupon) => {
        fulfillCard(coupon)
    })
}


/**
 * @param {string} name
 * @param {number} page
 * @return {Promise<void>}
 */
async function fillCardsWithCertificatesByName(name, page) {
    const data = await getCertificatesByName(name.toString(), Number.parseInt(page), defaultSize);
    const coupons = data._embedded.giftCertificateModelList;
    coupons.forEach((coupon) => {
        fulfillCard(coupon)
    })
}

/**
 * @param {string} description
 * @param {number} page
 * @return {Promise<void>}
 */
async function fillCardsWithCertificatesByDescription(description, page) {
    const data = await getCertificatesByDescription(description.toString(), Number.parseInt(page), defaultSize);
    const coupons = data._embedded.giftCertificateModelList;
    coupons.forEach((coupon) => {
        fulfillCard(coupon)
    })
}

/**
 *
 * @param {string} tags
 * @param {number }page
 * @return {Promise<void>}
 */
async function fillCardsWithCertificatesByTags(tags, page) {
    const data = await getCertificatesByTags(tags, Number.parseInt(page), defaultSize);
    const coupons = data._embedded.giftCertificateModelList;
    coupons.forEach((coupon) => {
        fulfillCard(coupon)
    })
}


/**
 * This function creates HTML elements using retrieved data
 */
function fulfillCard(data) {
    const cardsContainer = document.getElementById('cards-container')

    const cardsBox = document.getElementById('cards-box-item');
    cardsBox.className = 'cards-box';

    const itemCard = document.createElement('div');
    itemCard.className = 'item-card';

    const imageDiv = document.createElement('div');
    imageDiv.className = 'w-100 h-60 bg-grey';

    const leftSection = document.createElement('div');
    leftSection.className = 'it-left-section';

    const couponName = document.createElement('p');
    couponName.className = 'text-left mt-10';
    couponName.textContent = data.name;

    const description = document.createElement('p');
    description.className = 'mt-3 text-muted text-left fs-10';
    description.textContent = data.description.substring(0, 30);

    const price = document.createElement('p');
    price.className = 'p-5 text-left';
    price.textContent = '$' + data.id;

    const rightSection = document.createElement('div');
    rightSection.className = 'it-right-section';

    const favoriteLink = document.createElement('a');
    favoriteLink.href = '#';
    favoriteLink.className = 'material-symbols-outlined';
    favoriteLink.textContent = 'favorite';

    const expiresParagraph = document.createElement('p');
    expiresParagraph.className = 'text-muted mt-3';
    expiresParagraph.style.fontSize = '9px';
    expiresParagraph.textContent = 'Expires in ' + data.duration + ' days';

    const addToCartButton = document.createElement('button');
    addToCartButton.className = 'mt-10';
    addToCartButton.textContent = 'Add to cart';

    leftSection.appendChild(couponName);
    leftSection.appendChild(description);
    leftSection.appendChild(price);

    rightSection.appendChild(favoriteLink);
    rightSection.appendChild(expiresParagraph);
    rightSection.appendChild(addToCartButton);

    itemCard.appendChild(imageDiv);
    itemCard.appendChild(leftSection);
    itemCard.appendChild(rightSection);

    cardsBox.appendChild(itemCard);


    cardsContainer.appendChild(cardsBox);

}

document.addEventListener("DOMContentLoaded", fillCardsWithAllCertificates, false)





let typingTimer;
const doneTypingInterval = 500;

/**
 * Function to be called when the user finish typing
 */
function searchQueryChanged() {
    nextPage = 0;
    const userInput = document.getElementById("search-input").value;
    const inputSize = userInput.length;
    if (selectedOption.textContent === 'Description' && inputSize > 0) {
        fillCardsWithCertificatesByDescription(userInput, 0);
    } else if (selectedItem.textContent === 'Name' && inputSize > 0) {
        fillCardsWithCertificatesByName(userInput, 0);
    } else if (selectedItem.textContent === 'Tags' && inputSize > 0) {
        fillCardsWithCertificatesByTags(userInput,0);
    } else {
        fillCardsWithAllCertificates(0);
    }
}


const searchInput = document.getElementById("search-input");
searchInput.addEventListener("input", function () {
    const cardsBox = document.getElementById('cards-box-item');
    cardsBox.innerHTML = "";

    clearTimeout(typingTimer);
    typingTimer = setTimeout(searchQueryChanged, doneTypingInterval);
});

/**
 * This function sorts data by creation date
 * @param {any[]} data
 */
function sortByCreationDate(data) {
    data.sort((a, b) => new Date(b.createDate) - new Date(a.createDate));
}

