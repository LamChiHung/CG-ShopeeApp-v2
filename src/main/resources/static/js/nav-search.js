$("#pageNext").click(function (event) {
    let page = $("#page").val();
    $("#page").val(page + 1);
    console.log("a");
    $("form").submit();
})
$("#pagePrevious").click(function (event) {
    let page = $("#page").val();
    $("#page").val(page - 1);
    console.log("a");
    $(".nav-bg").submit();
})
$(".submit-button").click(function (event) {
    $("#page").val(0);
})