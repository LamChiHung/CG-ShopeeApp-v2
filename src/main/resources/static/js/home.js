setInterval(function () {
    let count = 0;
    let radio1 = document.querySelector("#radio1");
    let radio2 = document.querySelector("#radio2");
    let radio3 = document.querySelector("#radio3");
    let radio4 = document.querySelector("#radio4");

    if (radio1.checked) {
        count = 1;
    }
    if (radio2.checked) {
        count = 2;
    }
    if (radio3.checked) {
        count = 3;
    }
    if (radio4.checked) {
        count = 4;
    }

    if (count < 4) {
        count++;
        document.querySelector(`#radio${count}`).checked = true;
    } else {
        count = 1;
        document.querySelector(`#radio${count}`).checked = true;
    }
    

}, 3000);

