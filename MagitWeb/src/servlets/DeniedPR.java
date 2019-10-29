package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.Branch;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;
import Users.Message;
import Users.PR;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeniedPR extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repo = SessionUtils.getRepo(request);
        String denyReason=request.getParameter("denyReason");
        PR pr=repo.PrMap.get(SessionUtils.getPrDeltaUsername(request));
        repo.getHeadBranch().UpdateSha1(pr.SenderCommitSha1);
        Repository localRepo = UsersDataBase.getUserData(pr.Sender).repoMap.get(pr.RepoName);
        Message msg= new Message(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),
                pr.SenderBranch,"PrDenied",denyReason);
        UsersDataBase.addMessageToUser(pr.Sender,msg);
        repo.PrMap.remove(pr.Sender);
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}