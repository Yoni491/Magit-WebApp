package servlets;

import Objects.Api.MagitObject;
import Objects.Branch.AlreadyExistingBranchException;
import Objects.Branch.Branch;
import Objects.Branch.BranchNoNameException;
import Objects.Branch.NoCommitHasBeenMadeException;
import Repository.Repository;
import Users.Message;
import Users.UserData;
import Users.UsersDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static servlets.SessionUtils.getRepo;


public class Push6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String branchName = request.getParameter("branchName");
        if(branchName!=null)
        {
            Repository localRepo=getRepo(request);
            Repository remoteRepo = UsersDataBase.getRepo(localRepo.getRemoteRepoName(), localRepo.getRemoteRepoUserName());

            Branch br=localRepo.branchLambda_ex3(branchName);
            for (Map.Entry<String, MagitObject> entry : localRepo.getObjList().entrySet()) {
                if (!remoteRepo.getObjList().entrySet().contains(entry))
                    remoteRepo.getObjList().put(entry.getKey(), entry.getValue());
            }
            try {
                remoteRepo.addNewBranch(br.getName(),br.getSha1(),"local");
            br.setType("tracking");
            localRepo.addNewBranch(br.getName(),br.getSha1(),"remote");
            } catch (AlreadyExistingBranchException | BranchNoNameException | NoCommitHasBeenMadeException e) {
                e.printStackTrace();
            }

        }
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}