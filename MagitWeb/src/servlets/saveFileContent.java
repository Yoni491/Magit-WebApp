package servlets;

import Objects.Blob.Blob;
import Repository.Repository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class saveFileContent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Repository repo =SessionUtils.getRepo(request);
        String filePath = request.getParameter("filePath");
        String newContent = request.getParameter("fileContent");
        if(filePath!=null) {
            SessionUtils.setBlobSha1(request,repo.saveFileContent_ex3(filePath,newContent));
        }
        response.sendRedirect("../WcPage/WcPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}