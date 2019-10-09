//
// $(function () {
//     var uploadRepositoryBtn=$("#uploadRepositoryBtn");
//     var msg=$("#s");
//
//     uploadRepositoryBtn.bind("click",function () {
//         $.ajax({
//             data:uploadRepositoryBtn,
//             url: 'upload',
//             success:function () {
//                 msg.text("you logged in");
//                 msg.css("color","black");
//                 window.location.replace("../../Pages/UserPage/UserPage.jsp");
//             },
//             error: function (xhr) {
//                 if(xhr.status===403){
//                     msg.text("no such username!");
//                     msg.css("color","red")
//                 }
//                 else{
//                     msg.text("failed to get result from server")
//                 }
//             }
//         });
//
//     })
// });
    // locate your element and add the Click Event Listener
    document.getElementById("allUsers").addEventListener("click",function() {
    // e.target is our targetted element.
    // try doing console.log(e.target.nodeName), it will result LI
        setUsernameForRepos(request,"");
});
