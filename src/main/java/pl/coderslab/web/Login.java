package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();

        if (sess.getAttribute("enable") == null) {
            getServletContext().getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
        if (sess.getAttribute("enable").equals(1)) {
            response.sendRedirect("/app/dashboard");
          
        } else {
            getServletContext().getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession sess = request.getSession();

        AdminDao adminDao = new AdminDao();

        Admin admin = adminDao.LoginSearch(email);

        if (admin.getEmail() == null) {
            getServletContext().getRequestDispatcher("/login-failed.jsp")
                    .forward(request, response);
        } else if (!(BCrypt.checkpw(password, admin.getUnsecurePassword()))) {
            getServletContext().getRequestDispatcher("/login-failed.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("/app/dashboard");
            sess.setAttribute("enable", admin.getEnable());
            sess.setAttribute("id", admin.getId());
            sess.setAttribute("name", admin.getFirstName());

        }
    }
}
