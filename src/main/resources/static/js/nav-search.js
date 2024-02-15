$("#pageNext").click(function (event) {
    let page = $("#page").val();
    $("#page").val((Number(page) + 1));
    console.log($("#page").val());
    $(".nav-bg").submit();
})
$("#pagePrevious").click(function (event) {
    let page = $("#page").val();
    $("#page").val((Number(page) - 1));
    console.log($("#page").val());
    $(".nav-bg").submit();
})
$(".submit-button").click(function (event) {
    $("#page").val(0);
})

$(".content-container-sort-item").mouseup(function (event) {
    setTimeout(submit, 200);
})

function submit() {
    $(".nav-bg").submit();
}