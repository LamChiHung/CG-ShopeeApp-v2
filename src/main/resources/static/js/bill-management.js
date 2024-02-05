


var submitBill = document.querySelectorAll('.bill-button');

submitBill.forEach(function (button) {
    button.addEventListener('click', function () {
        var billButton = button.parentNode.parentNode;

        var submit = document.getElementById('submit-bill');
        var cancelButton = document.getElementById('exist-button')
        var overlay2 = document.querySelector('.overlay-bill-2')

        billButton.addEventListener('click', function () {
            submit.style.display = 'block';
            overlay2.style.display = 'block';
        })

        cancelButton.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay2.style.display = 'none';
        })
        overlay2.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay2.style.display = 'none';
        });

    })
})




var deleteBill = document.querySelectorAll('.delete-bill-button');

deleteBill.forEach(function (button) {
    button.addEventListener('click', function () {
        var billButton = button.parentNode.parentNode;

        var submit = document.getElementById('delete-bill');
        var cancelButton = document.getElementById('exist-bill-button')
        var overlay = document.querySelector('.overlay-bill-2')

        billButton.addEventListener('click', function () {
            submit.style.display = 'block';
            overlay.style.display = 'block';
        })

        cancelButton.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay.style.display = 'none';
        })
        overlay.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay.style.display = 'none';
        });

    })
})