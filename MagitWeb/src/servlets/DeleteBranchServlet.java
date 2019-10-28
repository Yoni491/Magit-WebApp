package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.Branch;
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

public class DeleteBranchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoCommitHasBeenMadeException | BranchNoNameException | AlreadyExistingBranchException e) {
            e.printStackTrace();
        }
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoCommitHasBeenMadeException, BranchNoNameException, AlreadyExistingBranchException {
        String branchName = request.getParameter("branchName");
        Repository localRepo = SessionUtils.getRepo(request);
        Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(), localRepo.getRemoteRepoUserName());

        Branch branchToRemove = localRepo.getBranches().stream().filter(br->br.getName().equals(branchName)).findFirst().orElse(null);
        String branchNameMsg=branchToRemove.getName();
        if(branchToRemove.getType().equals("local")){
            localRepo.removeLocalBranch(branchToRemove);
        }
        else
            localRepo.removeRbBranch(branchToRemove,remoteRepo);
        Message msg= new Message(localRepo.getName(), SessionUtils.getUsername(request), localRepo.getRemoteRepoUserName(),branchNameMsg);
        UsersDataBase.addMessageToUser(remoteRepo.getUsername(),msg);
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (NoCommitHasBeenMadeException | AlreadyExistingBranchException | BranchNoNameException e) {
            e.printStackTrace();
        }
    }
}