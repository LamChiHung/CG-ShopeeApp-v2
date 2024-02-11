
var deleteHistory = document.querySelectorAll('.button-delete-history');

deleteHistory.forEach(function (button){
    button.addEventListener('click',function (){
        var historyDelete = button.parentNode.parentNode;

        var deleteVoucher = document.getElementById('delete-history');
        var cancel = document.getElementById('cancel-delete-history-button')
        var overlay2 = document.querySelector('.overlay-2')

        historyDelete.addEventListener('click',function (){
            deleteVoucher.style.display = 'block';
            overlay2.style.display = 'block';
            console.log('a')
        })

        cancel.addEventListener('click', function (){
            formContainer.style.display = 'none';
            overlay.style.display = 'none';
        })
        overlay2.addEventListener('click', function () {
            formContainer.style.display = 'none';
            overlay.style.display = 'none';
            console.log('b')
        });
    })
})
