window.addEventListener('load', function () {
    var discountTypeSelect = document.querySelector('.edit-choose');
    var discountValueDiv = document.querySelector('.unit');
    discountTypeSelect.addEventListener('change', function () {
        var selectedOption = discountTypeSelect.options[discountTypeSelect.selectedIndex];
        var selectedValue = selectedOption.value;
        if (selectedValue === 'price') {
            discountValueDiv.textContent = 'VND';
        } else {
            discountValueDiv.textContent = '%';
        }
    });
});

var choose = document.getElementById('edit-choose');
var unit = document.getElementById('unit');
var label = document.querySelector('#edit-change-price');
var overlay3 = document.querySelector('.overlay');
choose.addEventListener('click', function () {

})


var changeInfoButton = document.getElementById('changeInfo');
var formContainer = document.getElementById('formContainer');
var overlay = document.querySelector('.overlay');
changeInfoButton.addEventListener('click', function () {
    formContainer.style.display = 'block';
    overlay.style.display = 'block';
});

overlay.addEventListener('click', function () {
    formContainer.style.display = 'none';
    overlay.style.display = 'none';
});


var deleteVoucher = document.querySelectorAll('.button-delete');

deleteVoucher.forEach(function (button) {
    button.addEventListener('click', function () {
        var voucherDelete = button.parentNode.parentNode;
        console.log(voucherDelete.textContent)

        var deleteVoucher = document.getElementById('delete-voucher');
        var cancelButton = document.getElementById('cancel-button')
        var overlay2 = document.querySelector('.overlay-2')

        voucherDelete.addEventListener('click', function () {
            deleteVoucher.style.display = 'block';
            overlay2.style.display = 'block';
            console.log(voucherDelete.textContent)
        })

        cancelButton.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay2.style.display = 'none';
        })
        overlay2.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay2.style.display = 'none';
            console.log('b')
        });

    })
})

let editVoucher = document.querySelectorAll(".edit-voucher");
editVoucher.forEach(element => {
    element.addEventListener("click", function (event) {


        var edit = document.querySelector('.overlay-3');
        var formEdit = document.getElementById('edit-form');
        var unit = document.getElementById('unit')
        var choose = document.getElementById('edit-choose')
        let parent = event.target.parentElement.parentElement;
        parent.addEventListener('click', function () {
            formEdit.style.display = 'block';
            edit.style.display = 'block';
            document.getElementById("voucher-code").value = parent.children[0].textContent.trim();
            document.getElementById("voucher-condition").value = parent.children[1].textContent.trim();
            document.getElementById("edit-choose").value = parent.children[2].textContent.trim();
            var chooseValue = document.getElementById("edit-choose").value = parent.children[2].textContent.trim();
            document.getElementById("unit").value = parent.children[4].textContent.trim();
            var unitPrice = document.getElementById("unit").value = parent.children[3].textContent.trim();

            if (chooseValue === 'phần trăm') {
                choose.selectedIndex = 0;
                unit.textContent = '%';
                document.getElementById("money").value = parent.children[3].textContent.trim();
            } else {
                choose.selectedIndex = 1;
                unit.textContent = 'VND'
                document.getElementById("money").value = parent.children[3].textContent.trim();
            }
        })
    });
})

