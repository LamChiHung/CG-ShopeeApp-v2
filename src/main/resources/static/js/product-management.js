let closeButton = document.querySelector(".addProduct-container-form-cancel-button");
let cancelButton = document.querySelector(".addProduct-container-form-buttonGroup-cancel");
let submitButton = document.querySelector(".addProduct-container-form-buttonGroup-save");
let form = document.querySelector(".addProduct-container");
let addProductButton = document.querySelector(".product-container-buttonContainer-addButton");
let editProductButton = document.querySelectorAll(".product-container-row2-item-information-edit");

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

editProductButton.forEach(element => {
    element.addEventListener("click", function(event){
        form.style.display = "flex";
        let a = document.querySelector("#a-form");
        a.value = "edit";
        let parent = event.target.parentElement.parentElement;

        document.getElementById("a-form").value = "edit";
        document.getElementById("product-id-form").value = parent.children[0].textContent.trim();
        document.getElementById("product-name-form").value = parent.children[2].textContent.trim();
        document.getElementById("category-form").value = parent.children[3].textContent.trim();
        console.log(document.getElementById("category-form"));
        document.getElementById("product-originPrice-form").value = parent.children[4].textContent.trim();
        document.getElementById("product-sellPrice-form").value = parent.children[5].textContent.trim();
        document.getElementById("product-sellPrice-form").value = parent.children[5].textContent.trim();
        document.getElementById("product-quantity-form").value = parent.children[6].textContent.trim();
        document.getElementById("product-urlAvatar-form").value = parent.children[1].children[0].src.trim();
    });
})


//
document.querySelectorAll(".product-container-row2-item-information-delete").forEach(function(deleteLink) {
    deleteLink.addEventListener("click", function(event) {
        event.preventDefault();
        var confirmationPopup = this.parentNode.querySelector(".popup");
        confirmationPopup.style.display = "block";

        var cancelButton = confirmationPopup.querySelector("#cancelButton");
        cancelButton.addEventListener("click", function() {
            confirmationPopup.style.display = "none";
        });

        // var confirmButton = confirmationPopup.querySelector("#confirmButton");
        // confirmButton.addEventListener("click", function() {
        //     var productId = this.parentNode.parentNode.querySelector(".product-container-row2-item-information").textContent.trim();

            // var form = document.createElement("form");
            // form.setAttribute("action", "/product");
            // form.setAttribute("method", "post");
            //
            // var input = document.createElement("input");
            // input.setAttribute("type", "hidden");
            // input.setAttribute("name", "productId");
            // input.setAttribute("value", productId);

            // form.appendChild(input);
            // document.body.appendChild(form);
            // form.submit();
        });
    });

// });