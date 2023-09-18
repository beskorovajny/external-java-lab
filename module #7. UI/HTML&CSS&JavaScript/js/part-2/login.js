/**
 * Login request
 * @param {string} email
 * @param {string} password
 * @return {Promise<any|*[]>}
 */
async function login(email, password) {
    try {
        const url = `http://localhost:8080/api/auth/sign-in`
        const requestBody = {
            email: email,
            password: password
        };

        const requestBodyJSON = JSON.stringify(requestBody);

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: requestBodyJSON,
        };

        const response = await fetch(url, requestOptions);
        if (!response.ok) {
            alert('Wrong email or/and password')
            return
        }
        return response.json();
    } catch (error) {
        console.error('Failure in login request:', error.message, error.stack)
        return []
    }
}


async function processLogin() {
    const homePage = 'http://localhost:63342/external-java-lab/module%20%237.%20UI/HTML&CSS/home.html?_ijt=qlphio5aveel866866rp8d9kov&_ij_reload=RELOAD_ON_SAVE'
    const email = document.getElementById('email').value
    const password = document.getElementById('password').value
    const data = await login(email, password)
    const token = data.accessToken

    try {
        saveTokenToLocalStorage(token)
        window.location.href = homePage
    } catch (error) {
        console.error('Failure in processLogin:', error.message, error.stack)
        return []
    }
}

/**
 * Function for save received token from login request into browser local storage
 * @param {string} token
 */
function saveTokenToLocalStorage(token) {
    alert('removing old token')
    localStorage.removeItem('token')
    alert('Saving token to storage')
    alert(token.toString())
    if (token.length <= 0) {
        alert('Invalid token')
        throw new Error('Invalid token format');
    }
    localStorage.setItem('token', token);
}

const loginButton = document.getElementById('submitLogin')

loginButton.addEventListener('click', processLogin, false)


