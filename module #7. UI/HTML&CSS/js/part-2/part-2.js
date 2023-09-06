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
 *
 */