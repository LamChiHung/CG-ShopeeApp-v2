let cancelButton = document.querySelector(".message-popup-container-button-cancel");
let starPopup = document.querySelector(".message-popup-container-star");
let ratingButtons = document.querySelectorAll(".rating-button");
ratingButtons.forEach(ratingButton => {
    ratingButton.addEventListener('click', function (event) {
        starPopup.style.display = 'flex';
        console.log(this.getAttribute("id"));
        document.getElementById("productId").value = this.getAttribute("id");
    })
})
cancelButton.addEventListener('click', function (event) {
    starPopup.style.display = 'none';
})