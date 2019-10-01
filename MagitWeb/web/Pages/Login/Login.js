
$(function () {
    var userName=$("#userName");
    var msg=$("#s");
    $("#logInBtn").bind("click",function () {
        $.ajax({
            data:userName,
            url: '/Login',
            success:function () {
                msg.text("you logged in");
                msg.css("color","black");
                location.href='Pages/UserPage/UserPage.html';
            },
            error: function (xhr, status, error) {
                if(xhr.status===403){
                    msg.text("name already exist!");
                    msg.css("color","red")
                }
                else{
                    msg.text("failed to get result from server")
                }
            }
        });
    })
})