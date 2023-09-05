'use strict';
/**
 *
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
            return data._embedded.giftCertificateModelList; // This is your JSON data
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

getCertificates(0, 10);


