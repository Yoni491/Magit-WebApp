//
// $(function () {
//     var btnPressed=$("#pressed");
//     btnPressed.bind("click",function () {
//         $.ajax({
//             data:this.val(),
//             url: 'selectUser',
//             success:function () {
//                 window.location.replace("../../Pages/UserPage/UserPage.jsp");
//             }
//         });
//     })
// });
function refreshUsersList(users) {
    //clear all current users
    $("#MessageList").empty();

    // rebuild the list of users: scan all users and add them to the list of users
    $.each(users || [], function(index, username) {
        console.log("Adding user #" + index + ": " + username);
        //create a new <option> tag with a value in it and
        //appeand it to the #userslist (div with id=userslist) element
        $('<li>' + username + '</li>').appendTo($("#MessageList"));
    });
}
function ajaxUsersList() {
    $.ajax({
        url: 'ajaxMessageServlet',
        success: function(users) {
            refreshUsersList(users);
        }
    });
}



$(function() {
    setInterval(ajaxUsersList, 2000);
});
//הפונקציה שמעדכנת את הצ'אט