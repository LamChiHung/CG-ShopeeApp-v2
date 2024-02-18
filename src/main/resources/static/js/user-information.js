function showHideList() {
    let list = document.querySelectorAll(".person-body-container-nav-body-item2-item-listItem");
    if (list[0].style.display == "none" || list[0].style.display == "") {
        console.log(list[0].style.display);
        list.forEach(element => {
            element.style.display = "flex";
        });
    } else {
        console.log(list[0].style.display);
        list.forEach(element => {
            element.style.display = "none";
        });
    }
}

let savePW = document.querySelector(".changePassword");
savePW.onclick = function () {
    let el = document.querySelector(".change-password-container-content-item-2-form")
    let container = document.querySelector(".changeForm-container")
    container.style.display = 'flex'
    el.style.display = 'flex'
    // el.style.top = window.innerHeight/2-60+ 'px'
    // el.style.left = window.innerWidth/2-60 +'px'
    console.log(window.innerWidth, window.innerHeight)
    document.querySelector('.person-body').style.opacity = 0.2
    document.querySelector('.person-body').style.pointerEvents = 'none';
}
let closeFromPW = document.querySelector('.ti-close');
closeFromPW.onclick = function (e) {
    let el = document.querySelector(".change-password-container-content-item-2-form")
    let container = document.querySelector(".changeForm-container")
    container.style.display = 'none'
    el.style.display = 'none'
    document.querySelector('.person-body').style.opacity = 1
    document.querySelector('.person-body').style.pointerEvents = 'auto';
}


var userGenderInput = document.getElementById('gender-user-information');
var value = userGenderInput.value;

var radioButtons = document.getElementsByName('gender');
for (var i = 0; i < radioButtons.length; i++) {
    if (radioButtons[i].value === value) {
        radioButtons[i].checked = true;
        break;
    }
}

    // let imgContainer = document.querySelector('.person-body-container-content-bottom-right-top-avatar');
    // let imageInput = document.getElementById('img');
    // let imageUrl = imageInput.value;
    // imgContainer.style.backgroundImage = `url(${imageUrl})`;
    // alert(imageUrl);


