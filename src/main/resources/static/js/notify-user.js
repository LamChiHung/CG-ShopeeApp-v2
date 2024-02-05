let notifies = document.querySelectorAll(".person-body-container-content-bottom-group");
notifies.forEach(notify => {
    notify.addEventListener("click", function (e) {
        notify.style.backgroundColor = "#fff";
    })
})