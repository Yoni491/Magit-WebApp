$(function () {
    var userName=$("#userName");
    $("#logInBtn").click(function () {
        $.ajax({
            data:userName,
            url: '/Login',
            success:function () {
                $("#s").text("you logged in");
                $("#s").css("color","black");
                location.href='/pages/userPage/UserPage.html';
            },
            error: function (xhr, status, error) {
                if(xhr.status===403){
                    $("#s").text("name already exist!");
                    $("#s").css("color","red")
                }
                else{
                    $("#s").text("failed to get result from server")
                }
            }
        });
    })
})