let message: string = 'Hello, World!';
const greeting = document.getElementById("greeting")
const button = document.getElementById("push")
button.addEventListener('click', function () {
    greeting.textContent = message;
}, false)

console.log(message)