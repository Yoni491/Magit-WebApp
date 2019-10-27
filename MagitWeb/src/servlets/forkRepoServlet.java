package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class forkRepoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String press = request.getParameter("forkRepoName");
        String username = request.getParameter("ForkUsername");
        Repository repo= null;
            repo = new Repository(UsersDataBase.getRepo(press,username));
        repo.updateUsername(SessionUtils.getUsername(request));
        UsersDataBase.getUserData(SessionUtils.getUsername(request)).addForkedRepo(repo.getName());
        UsersDataBase.addRepo(SessionUtils.getUsername(request),repo.getName(),repo);
        response.sendRedirect("../UserPage/UserPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}