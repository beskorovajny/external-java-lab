'use strict';

/**
 * @param {number} page
 * @param {number} size
 * @return {*}
 */
function getCertificates(page, size) {
    return fetch(`http://localhost:8080/api/certificates/find-all?page=${page}&size=${size}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data._embedded.giftCertificateModelList)
            return data._embedded.giftCertificateModelList;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

getCertificates(0, 10);

/**
 * Scroll to top and restore position functionality
 *
 */

const scrollTopBtn = document.getElementById("scrollToTopBtn");
const restoreBtn = document.getElementById("restoreBtn");
let lastPosition = 0;

window.addEventListener("scroll", () => {
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


scrollTopBtn.onclick = function() {
    restoreBtn.style.display = "block";
}

restoreBtn.onclick = function() {
    restoreBtn.style.display = "none";
    lastPosition = 0;
}


/**
 * Fulfill card
 */
function fulfillCard() {
    // Create the outer div with class "cards-box"
    const cardsBox = document.createElement('div');
    cardsBox.className = 'cards-box';

// Create the inner div with class "item-card"
    const itemCard = document.createElement('div');
    itemCard.className = 'item-card';

// Create the first inner div with class "w-100 h-60 bg-grey"
    const imageDiv = document.createElement('div');
    imageDiv.className = 'w-100 h-60 bg-grey';

// Create the div with class "it-left-section"
    const leftSection = document.createElement('div');
    leftSection.className = 'it-left-section';

// Create the "Coupon name" paragraph
    const couponName = document.createElement('p');
    couponName.className = 'text-left mt-10';
    couponName.textContent = 'Coupon name';

// Create the description paragraph
    const description = document.createElement('p');
    description.className = 'mt-3 text-muted text-left fs-10';
    description.textContent = 'Some brief description';

// Create the price paragraph
    const price = document.createElement('p');
    price.className = 'mt-15 text-left';
    price.textContent = '$235';

// Create the div with class "it-right-section"
    const rightSection = document.createElement('div');
    rightSection.className = 'it-right-section';

// Create the "favorite" link
    const favoriteLink = document.createElement('a');
    favoriteLink.href = '#';
    favoriteLink.className = 'material-symbols-outlined';
    favoriteLink.textContent = 'favorite';

// Create the "Expires in N days" paragraph
    const expiresParagraph = document.createElement('p');
    expiresParagraph.className = 'text-muted mt-3';
    expiresParagraph.textContent = 'Expires in N days';

// Create the "Add to cart" button
    const addToCartButton = document.createElement('button');
    addToCartButton.className = 'mt-10';
    addToCartButton.textContent = 'Add to cart';

// Append the elements to their respective parent elements
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

// Finally, append the "cardsBox" div to the document body or any other container you want.
    document.body.appendChild(cardsBox);

}