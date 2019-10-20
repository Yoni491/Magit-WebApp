package servlets;

import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BranchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String branchName = request.getParameter("branch");
        String isPr = request.getParameter("isPR");
        String branchSha1 = request.getParameter("branchSha1");
        if(isPr!=null)
        {
            SessionUtils.setPrBranchName(request,branchName);
        }
        if(branchName!=null) {
            SessionUtils.setBranch(request,branchName);
            SessionUtils.setPrBranchName(request,"");
        }
        SessionUtils.setCommit(request,branchSha1);
        response.sendRedirect("../RepositoryPage/RepoPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}