
$(function () {
    var btnPressed=$("#pressed");
    btnPressed.bind("click",function () {
        $.ajax({
            data:this.val(),
            url: 'selectUser',
            success:function () {
                window.location.replace("../../Pages/UserPage/UserPage.jsp");
            }
        });
    })
});
