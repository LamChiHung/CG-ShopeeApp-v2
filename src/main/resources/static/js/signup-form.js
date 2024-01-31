let submitButton = document.getElementById("signup-submit-button");
console.log(submitButton);
submitButton.disabled = true;
let passwordRegex = false;
let passwordCorrect = false;

function hideShowPassword() {
    let openEye = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword-openEyeIcon");
    let password = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword");
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

function checkValidCorrectPassword() {
    let password = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword");
    let correctPassword = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputCorrectPassword");
    let messageCorrectPassword = document.querySelector(".invalidCorrectPassword-announce");
    let checkIcon = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputCorrectPassword-checkIcon");

    if (password.value != correctPassword.value) {
        messageCorrectPassword.style.display = "block";
        checkIcon.style.display = "none"
        return false;
    } else {
        messageCorrectPassword.style.display = "none";
        checkIcon.style.display = "block"
        return true;
    }
}


function checkRegexPassword() {
    var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
    let password = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword");
    let messageRegexPassword = document.querySelector(".invalidRegexPassword-announce");

    if (!password.value.match(passwordRegex) && password.value != "") {
        messageRegexPassword.style.display = "block";
        return false;
    } else {
        messageRegexPassword.style.display = "none";
        return true;
    }

}

function checkInput() {
    let email = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputEmail");
    let password = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword");
    let submitButton = document.querySelector(".signup-container-content-item-2-form-body-item-1-submitsignup");
    passwordRegex = checkRegexPassword();
    passwordCorrect = checkValidCorrectPassword();
    if (email.value != "" && password.value != "" && passwordRegex && passwordCorrect) {
        console.log(checkRegexPassword);
        submitButton.disabled = false;
    } else {
        submitButton.disabled = true;
    }
}

function checkInputMessage() {
    let email = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputEmail");
    let password = document.querySelector(".signup-container-content-item-2-form-body-item-1-inputPassword");
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