package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PrChangesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String PrDeltaUsername = request.getParameter("PrDeltaUsername");
        if(PrDeltaUsername!=null)
        {
            SessionUtils.setPrDeltaUsername(request,PrDeltaUsername);
            //צריך לעדכן את הענף ברפוסיטורי הקודם לremote

        }
        response.sendRedirect("../ShowDeltaPage/ShowDeltaPage.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);}
}