package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;
import Users.Message;
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
        String forkRepoName = request.getParameter("forkRepoName");
        String forkUsername = request.getParameter("ForkUsername");
        Repository localRepo = new Repository(UsersDataBase.getRepo(forkRepoName,forkUsername));
        localRepo.updateUsername(SessionUtils.getUsername(request));
        UsersDataBase.getUserData(SessionUtils.getUsername(request)).addForkedRepo(localRepo.getName());
        UsersDataBase.addRepo(SessionUtils.getUsername(request),localRepo.getName(),localRepo);
        response.sendRedirect("../UserPage/UserPage.jsp");
        Message msg= new Message(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName());
        UsersDataBase.addMessageToUser(forkUsername,msg);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}