package servlets;

import Objects.Api.MagitObject;
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
import java.util.Map;

import static servlets.SessionUtils.getRepo;

public class PushServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            Repository localRepo=getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(), localRepo.getRemoteRepoUserName());
            Branch br=localRepo.getHeadBranch();
        for (Map.Entry<String, MagitObject> entry : localRepo.getObjList().entrySet()) {
            if (!remoteRepo.getObjList().entrySet().contains(entry))
                remoteRepo.getObjList().put(entry.getKey(), entry.getValue());
        }
            remoteRepo.branchLambda_ex3(br.getName()).UpdateSha1(br.getSha1());

        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}