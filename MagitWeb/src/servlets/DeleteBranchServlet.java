package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.Branch;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;
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
        Repository repo = SessionUtils.getRepo(request);
        Repository remoteRepo = UsersDataBase.getRepo(repo.getRemoteRepoName(), repo.getRemoteRepoUserName());

        Branch branchToRemove = repo.getBranches().stream().filter(br->br.getName().equals(branchName)).findFirst().orElse(null);
        if(branchToRemove.getType().equals("local")){
            repo.removeLocalBranch(branchToRemove);
        }
        else
            repo.removeRbBranch(branchToRemove,remoteRepo);
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