let phone = document.getElementById('phone');
phone.addEventListener("keyup", function (e) {
    let regex = /[^0-9]/;
    let number = phone.value;
    phone.value = number.replace(regex, "");
})

let add = document.querySelector(".person-body-container-content-top-right-button");
add.addEventListener("click", function (e) {
    let formContainer = document.querySelector(".address-form-container");
    formContainer.style.display = "flex";
    let addForm = document.getElementById("addForm");
    addForm.style.display = "flex";
})

let closeAdd = document.getElementById("closeAdd");
closeAdd.addEventListener("click", function (e) {
    let formContainer = document.querySelector(".address-form-container");
    formContainer.style.display = "none";
    let addForm = document.getElementById("addForm");
    addForm.style.display = "none";
})