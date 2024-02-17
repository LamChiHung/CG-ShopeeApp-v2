let plusButtons = document.querySelectorAll(".plusButton");
let minusButtons = document.querySelectorAll(".minusButton");
let quantityValue = document.querySelectorAll(".quantityValue");


for (let index = 0; index < plusButtons.length; index++) {
    plusButtons[index].addEventListener("click", function (event) {
        let buttonClicked = event.target;
        let parentButtonClicked = buttonClicked.parentElement;
        let quantity = parentButtonClicked.children[1];
        if (Number.parseInt(quantity.value) < 1000) {
            quantity.value = (Number.parseInt(quantity.value) + 1);
        }
        console.log(quantity);
        calculator(parentButtonClicked, quantity);
    })
}
for (let index = 0; index < minusButtons.length; index++) {
    minusButtons[index].addEventListener("click", function (event) {
        let buttonClicked = event.target;
        let parentButtonClicked = buttonClicked.parentElement;
        let quantity = parentButtonClicked.children[1];
        if (Number.parseInt(quantity.value) > 1) {
            quantity.value = (Number.parseInt(quantity.value) - 1);
        }
        console.log(quantity);
        calculator(parentButtonClicked, quantity);

    })
}

for (let index = 0; index < quantityValue.length; index++) {
    quantityValue[index].addEventListener("blur", function (event) {
        let buttonClicked = event.target;
        let parentButtonClicked = buttonClicked.parentElement;
        let quantity = parentButtonClicked.children[1];
        if (quantity.value == "") {
            quantity.value = 1;
        }
        if (Number.parseInt(quantity.value) < 1) {
            quantity.value = 1;
        }
        if (Number.parseInt(quantity.value) > 999) {
            quantity.value = 999;
        }
        console.log(quantity);
        calculator(parentButtonClicked, quantity);

    })
}

function calculator(parentButtonClicked, quantity) {
    let rowContent = parentButtonClicked.parentElement.parentElement;
    console.log(rowContent);
    let originPrice = rowContent.children[2].children[1].children[1].textContent;
    let rowTotalPriceValue = Number.parseInt(originPrice.replaceAll(".", "")) * Number.parseInt(quantity.value);
    let rowTotalPrice = rowContent.children[4].children[1];
    rowTotalPrice.textContent = rowTotalPriceValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    let totalPrice = document.querySelector(".cart-body-container-footer-container-cartTotalMoney-money span");
    let totalPriceValue = 0;
    document.querySelectorAll(".cart-body-container-header-totalPrice p").forEach(element => {

        totalPriceValue += Number.parseInt(element.textContent.replaceAll(".", ""));
    })
    console.log(totalPrice);
    totalPrice.textContent = totalPriceValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    let totalQuantityValue = 0;
    document.querySelectorAll(".quantityValue").forEach(element => {
        totalQuantityValue += Number.parseInt(element.value);
    })
    console.log(totalQuantityValue);
    let totalQuantity = document.querySelector(".cart-body-container-footer-container-cartTotalMoney-info span");
    totalQuantity.textContent = totalQuantityValue;

    let deliveryCharge = document.querySelector(".cart-body-container-footer-container-deliveryCharge-money span");
    let totalBill = document.querySelector(".cart-body-container-footer-container-totalBill-money span");
    totalBill.textContent = (Number.parseInt(deliveryCharge.textContent.replaceAll(".", "")) + Number.parseInt(totalPrice.textContent.replaceAll(".", "")))
        .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

window.onload = (event) => {
    let totalPrice = document.querySelector(".cart-body-container-footer-container-cartTotalMoney-money span");
    let totalPriceValue = 0;
    document.querySelectorAll(".cart-body-container-header-totalPrice p").forEach(element => {
        totalPriceValue += Number.parseInt(element.textContent.replaceAll(".", ""));
    })
    console.log(totalPrice);
    totalPrice.textContent = totalPriceValue.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    let totalQuantityValue = 0;
    document.querySelectorAll(".quantityValue").forEach(element => {
        totalQuantityValue += Number.parseInt(element.value);
    })
    console.log(totalQuantityValue);
    let totalQuantity = document.querySelector(".cart-body-container-footer-container-cartTotalMoney-info span");
    totalQuantity.textContent = totalQuantityValue;

    let deliveryCharge = document.querySelector(".cart-body-container-footer-container-deliveryCharge-money span");
    let totalBill = document.querySelector(".cart-body-container-footer-container-totalBill-money span");
    totalBill.textContent = (Number.parseInt(deliveryCharge.textContent.replaceAll(".", "")) + Number.parseInt(totalPrice.textContent.replaceAll(".", "")))
        .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
