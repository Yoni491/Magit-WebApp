package servlets;

import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MakeNewBranchServlet extends HttpServlet {
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
        String newBranchName = request.getParameter("newBranch");
        if(newBranchName.equals(""))
            response.sendRedirect("../RepositoryPage/RepoPage.jsp");
        Repository repo = SessionUtils.getRepo(request);
        repo.addNewBranch(newBranchName,repo.getHeadBranch().getSha1());
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (NoCommitHasBeenMadeException e) {
            e.printStackTrace();
        } catch (BranchNoNameException e) {
            e.printStackTrace();
        } catch (AlreadyExistingBranchException e) {
            e.printStackTrace();
        }
    }
}