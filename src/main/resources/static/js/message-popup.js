let confirmButton = document.querySelector(".message-popup-container-button");
let messagePopup = document.querySelector(".message-popup-container");
confirmButton.addEventListener('click', function (event) {
    messagePopup.style.display = 'none';
})