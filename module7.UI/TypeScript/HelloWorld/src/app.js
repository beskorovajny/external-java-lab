var message = 'Hello, World!';
var greeting = document.getElementById("greeting");
var button = document.getElementById("push");
button.addEventListener('click', function () {
    greeting.textContent = message;
}, false);
console.log(message);
