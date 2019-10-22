package servlets;

import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ExecuteCommit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commitMsg = request.getParameter("commitMsg");
        Repository repo = SessionUtils.getRepo(request);
        if(repo.getWcHasOpenChanges()) {
            repo.executeCommit_ex3(commitMsg);
            SessionUtils.CommitSuccessOrFail="Committed successfully";
        }
        else
            SessionUtils.CommitSuccessOrFail="Can not commit";
        response.sendRedirect("../WcPage/WcPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}

