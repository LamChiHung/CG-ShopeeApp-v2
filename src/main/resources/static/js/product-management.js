let closeButton = document.querySelector(".addProduct-container-form-cancel-button");
let cancelButton = document.querySelector(".addProduct-container-form-buttonGroup-cancel");
let submitButton = document.querySelector(".addProduct-container-form-buttonGroup-save");
let form = document.querySelector(".addProduct-container");
let addProductButton = document.querySelector(".product-container-buttonContainer-addButton");


function closeForm() {
    form.style.display = "none";
}

function openAddProductForm() {
    document.querySelector(".addProduct-container-form").reset();
    form.style.display = "flex";
    let a = document.querySelector("#a-form");
    a.value = "add";
}

closeButton.addEventListener("click", closeForm);
cancelButton.addEventListener("click", closeForm);
addProductButton.addEventListener("click", openAddProductForm);

let editProductButton = document.querySelectorAll(".product-container-row2-item-information-edit");
editProductButton.forEach(element => {
    element.addEventListener("click", function (event) {
        form.style.display = "flex";
    });
})

document.querySelectorAll(".product-container-row2-item-information-delete").forEach(function (deleteLink) {
    deleteLink.addEventListener("click", function (event) {
        event.preventDefault();
        var confirmationPopup = this.parentNode.querySelector(".popup");
        confirmationPopup.style.display = "block";

        var cancelButton = confirmationPopup.querySelector("#cancelButton");
        cancelButton.addEventListener("click", function () {
            confirmationPopup.style.display = "none";
        });
    });
});
