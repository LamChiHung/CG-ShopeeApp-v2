
let username = document.getElementById('name');
if(username!=null){
    username.addEventListener('keyup', function (e) {
        let regex = /[^a-z A-Z]/;
        let text = username.value;
        text = text.replace(regex,"");
        username.value = text;
        let spaceRegex = /\s{2}/;
        text = text.replace(spaceRegex, " ");
        username.value = text;
    })
}

let phone = document.getElementById('phone_number');
if(phone!=null){
    phone.addEventListener('keyup', function (e) {
        let regex = /[^0-9]/;
        let number = phone.value;
        phone.value = number.replace(regex, "");
    })
}

let add = document.getElementById('add-address')
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


document.addEventListener('DOMContentLoaded', function () {
    let buttons = document.querySelectorAll('.button-update-address-user');
    let form = document.querySelector(".address-form-update-container");
    let formUpdate = document.querySelector('.address-update-form-container-form')
    let cancel = document.getElementById('address-cancel-button')
    buttons.forEach(function (button) {
        button.addEventListener('click', function () {
            form.style.display = 'block';
            formUpdate.style.display = 'block';
            let id = this.getAttribute('data-id');
            let name = this.getAttribute('data-name');
            let phone = this.getAttribute('data-phone');
            let city = this.getAttribute('data-city');
            let ward = this.getAttribute('data-ward');
            let district = this.getAttribute('data-district');
            let defaultAddress = this.getAttribute('data-default-address');
            let Ip = this.getAttribute('data-IP')
            let apartment_number = this.getAttribute('data-apartment');
            document.getElementById('IP').value = Ip;
            document.getElementById('id').value = id;
            document.getElementById('name').value = name;
            document.getElementById('phone').value = phone;
            document.getElementById('city').value = city;
            document.getElementById('ward').value = ward;
            document.getElementById('district').value = district;
            document.getElementById('apartment-number').value = apartment_number;
            document.getElementById('id-delete').value = id
            document.getElementById('default_address').value = defaultAddress;
            cancel.addEventListener('click', function () {
                form.style.display = 'none';
                formUpdate.style.display = 'none';
            })
        });
    });
});


let overlay = document.querySelector('.overlay-address')
let form_delete = document.querySelector('.form-delete')
let cancel = document.getElementById('cancel-button-form-delete');
var buttonDeletes = document.querySelectorAll('.btn-delete ');
buttonDeletes.forEach(function (button) {
    button.addEventListener('click', function () {
        let addressButton = button.parentNode.parentNode;
        document.getElementById('id-delete').value = this.getAttribute('data-id-delete');
        addressButton.addEventListener('click', function () {
            overlay.style.display = 'block';
            form_delete.style.display = 'block';
        })
        cancel.addEventListener('click', function () {
            form_delete.style.display = 'none';
            overlay.style.display = 'none';
            event.preventDefault()
        })
        overlay.addEventListener('click', function () {
            form_delete.style.display = 'none';
            overlay.style.display = 'none';
            event.preventDefault()
        });
    })
});

let buttonChangeAddress = document.querySelectorAll('.change-default-address');
let buttonDefaultAddress = document.querySelectorAll('.button-default-address');
buttonChangeAddress.forEach('button', function () {
    button.addEventListener('click', function () {
        buttonDefaultAddress.forEach(function (button) {
            button.addEventListener('click', function () {
                document.getElementById('change-default-address').value = 'false';
            });
        });
    })
})





