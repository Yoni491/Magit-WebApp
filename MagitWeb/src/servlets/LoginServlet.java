package servlets;

import Users.UsersDataBase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName;
        HttpSession session = request.getSession(false);
        if (session == null) {
            userName = null;
        } else {
            userName = session.getAttribute("username").toString();
        }
        response.sendError(403,userName);
//        if(userName==null){
//            String userNameFromRequest=request.getParameter("userName");
//            UserManager userManager=ServletUtils.getUserManaqer(getServletContext());
//            User user=new User(userNameFromRequest);
//            synchronized (this) {
//                if (!userManager.isUserExists(user)) {
//                    userManager.addUser(user);
//                    request.getSession(true).setAttribute(Constants.USERNAME,user.getName());
//                }
//                else{
//                    response.sendError(403,"user name already exist in system");
//                }
//            }
//        }
    }
}
