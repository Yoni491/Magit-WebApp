package servlets;

import EngineRunner.ModuleTwo;
import Users.UsersDataBase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName;
        userName=request.getParameter("userName");
        if(userName.equals(""))
            response.sendError(403,"Please enter a username");
        synchronized (this) {
            if(!UsersDataBase.usernameExists(userName)) {
                response.sendError(403,"User name doesn't exists");
            }
            else
                ModuleTwo.updateUsername(userName);
                request.getSession(true).setAttribute("userName", userName);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
