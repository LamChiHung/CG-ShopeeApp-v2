let add = document.querySelector(".seller-register-container-submit");
let formContainer = document.querySelector(".address-form-container");
let addForm = document.getElementById("addForm");
add.addEventListener('click', function () {
    formContainer.style.display = 'flex';
    addForm.style.display = 'flex';
})

let closeAdd = document.getElementById("closeAdd");
closeAdd.addEventListener("click", function (e) {
    let formContainer = document.querySelector(".address-form-container");
    formContainer.style.display = "none";
    let addForm = document.getElementById("addForm");
    addForm.style.display = "none";
})
let phone = document.getElementById('phone_number');

if (phone != null) {
    phone.addEventListener('keyup', function (e) {
        let regex = /[^0-9]/;
        let number = phone.value;
        phone.value = number.replace(regex, "");
    })
}
let username = document.getElementById('name');
if (username != null) {
    username.addEventListener('keyup', function (e) {
        let regex = /[^a-z A-Z]/;
        let text = username.value;
        text = text.replace(regex, "");
        username.value = text;
        let spaceRegex = /\s{2}/;
        text = text.replace(spaceRegex, " ");
        username.value = text;
    })
}