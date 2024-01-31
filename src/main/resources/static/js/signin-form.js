let submit = document.querySelector(".signin-container-content-item-2-form-body-item-1-submitLogin");
submit.disabled = true;


function hideShowPassword() {
    let openEye = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputPassword-openEyeIcon");
    let password = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputPassword");
    let passwordType = password.type;
    console.log(passwordType.typeOf)
    if (passwordType == "password") {
        password.type = "text";
        openEye.style.display = "block";
    } else {
        password.type = "password";
        openEye.style.display = "none";
    }
}

function checkInput() {
    let email = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputEmail");
    let password = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputPassword");
    let submit = document.querySelector(".signin-container-content-item-2-form-body-item-1-submitLogin");

    if (email.value != "" && password.value != "") {
        submit.disabled = false;
    } else {
        submit.disabled = true;
    }
}

function checkInputMessage() {
    let email = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputEmail");
    let password = document.querySelector(".signin-container-content-item-2-form-body-item-1-inputPassword");
    let messageEmail = document.querySelector(".invalidEmail-announce");
    let messagePassword = document.querySelector(".invalidPassword-announce");

    if (email.value != "") {
        messageEmail.style.display = "none";
    } else {
        messageEmail.style.display = "block";
    }
    if (password.value != "") {
        messagePassword.style.display = "none"
    } else {
        messagePassword.style.display = "block"
    }
}
