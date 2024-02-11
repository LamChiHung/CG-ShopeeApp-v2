var button = document.getElementById('change-information')
button.addEventListener('click', function () {
    var overlay = document.querySelector('.overlay');
    var form = document.getElementById('formContainer');
    var inputName = document.getElementById('name-seller-information');
    var inputAddress = document.getElementById('address-seller-information');
    var inputPhoneNumber = document.getElementById('phone-number-seller-information')

    overlay.style.display = 'block'
    form.style.display = 'block'
    inputName.addEventListener('input', function () {
        const name = inputName.value;
        const regex = /^[^0-9~`@!#$%^&*()_+{};:'<>/?]+$/;
        const lengthRegex = /^.{2,50}$/;
        if (!regex.test(name)) {
            inputName.setCustomValidity('Tên không được chứa số hay ký tự đặc biệt');
        } else if (!lengthRegex.test(name)) {
            inputName.setCustomValidity('Tên phải có từ 2 đến 50 ký tự');
        } else {
            inputName.setCustomValidity('');
        }
    })


    inputAddress.addEventListener('input', function () {
        const name = inputAddress.value;
        const regex = /^[a-zA-Z0-9\s]+$/;
        if (!regex.test(name)) {
            inputAddress.setCustomValidity('Địa chỉ không được chứa ký tự đặc biệt');
        } else {
            inputAddress.setCustomValidity('');
        }
    })

    inputPhoneNumber.addEventListener('input', function () {
        const name = inputPhoneNumber.value;
        const regex = /^0\d{9}$/;
        if (!regex.test(name)) {
            inputPhoneNumber.setCustomValidity('Số điện thoại không đúng định dạng');
        } else {
            inputPhoneNumber.setCustomValidity('');
        }
    })

    var cancel = document.getElementById('cancel-form-information');
    cancel.addEventListener('click', function () {
        overlay.style.display = 'none'
        form.style.display = 'none'
    })

})
