package servlets;

import Objects.Branch.Branch;
import Repository.Repository;
import Users.PR;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExecutePR extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repo = SessionUtils.getRepo(request);
        PR pr=repo.PrMap.get(SessionUtils.getPrDeltaUsername(request));
        repo.getHeadBranch().UpdateSha1(pr.SenderCommitSha1);
        Repository remoteRepo =UsersDataBase.getUserData(pr.Sender).repoMap.get(pr.RepoName);
        if(remoteRepo.getHeadBranch().getName().equals(pr.SenderBranch))
            remoteRepo.getHeadBranch().setType("remote");
        Branch branch=remoteRepo.getBranches().stream().filter(br->br.getName().equals(pr.SenderBranch)).findFirst().orElse(null);
        if(branch!=null)
            branch.setType("remote");
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}

