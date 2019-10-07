
$(function () {
    var userName=$("#userName");
    var msg=$("#s");
    $("#signUpBtn").bind("click",function () {
        $.ajax({
            data:userName,
            url: 'getSignUpServlet',
            success:function () {
                msg.text("you logged in");
                msg.css("color","black");

                window.location.replace("../../Pages/UserPage/UserPage.jsp");
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
        // $( "userName" ).data(userName);
        // $.get('getLoginServlet', function(data) {
        //     alert(data);
        // });

    })
});

$(function () {
    var userName=$("#userName");
    var msg=$("#s");
    $("#signInBtn").bind("click",function () {
        $.ajax({
            data:userName,
            url: 'getSignInServlet',
            success:function () {
                msg.text("you logged in");
                msg.css("color","black");
                window.location.replace("../../Pages/UserPage/UserPage.jsp");
            },
            error: function (xhr) {
                if(xhr.status===403){
                    msg.text("no such username!");
                    msg.css("color","red")
                }
                else{
                    msg.text("failed to get result from server")
                }
            }
        });
        // $( "userName" ).data(userName);
        // $.get('getLoginServlet', function(data) {
        //     alert(data);
        // });

    })
});