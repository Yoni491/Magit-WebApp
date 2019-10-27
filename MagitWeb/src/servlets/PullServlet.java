package servlets;

import Objects.Api.MagitObject;
import Objects.Branch.Branch;
import Repository.Repository;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static servlets.SessionUtils.getRepo;

public class PullServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Repository localRepo=getRepo(request);
        Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(), localRepo.getRemoteRepoUserName());
        String branchName = request.getParameter("branchName");
        for (Map.Entry<String, MagitObject> entry : remoteRepo.getObjList().entrySet()) {
            if (!localRepo.getObjList().entrySet().contains(entry))
                localRepo.getObjList().put(entry.getKey(), entry.getValue());
        }
        localRepo.branchLambda_ex3(branchName).UpdateSha1(remoteRepo.getHeadBranch().getSha1());
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}