package servlets;

import Users.UsersDataBase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "servlets.SignupServlet", urlPatterns = "/Signup")

public class SignupServlet extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String error = null;
        boolean redirect=false;
        if (username == null) {
            username = "";
        }
        else
            error="Please enter a username.";
        if (!username.isEmpty()) {
            if(!UsersDataBase.usernameExists(username)){
                UsersDataBase.addUserName(username);
                redirect=true;

            }
            else
                error=username + "Already exists.";
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Sign up</title>");
            out.println("</head>");
            out.println("<body>");
            if(redirect)
                out.println("<meta http-equiv = 'refresh' content = '0; url = /Magit/Home' />");


            out.println("<form action='Signup'>");
            out.println("    <input type='text' name='username' value='" +username+ "'/>");
            out.println("    <input type='submit' value='Sign up' />");
            out.println("</form>");


            if (error != null) {
                out.println("<h2>Error: " + error + "</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

