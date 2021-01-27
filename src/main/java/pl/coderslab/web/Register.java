package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = new Admin();
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        session.setAttribute("name", name);
        session.setAttribute("surname", surname);
        session.setAttribute("email", email);
        session.setAttribute("password", password);
        session.setAttribute("re-password", request.getAttribute("re-password"));

        Pattern nameReg = Pattern.compile("[A-Za-z]{3,}");
        Matcher nameMatch = nameReg.matcher(name);
        if (!nameMatch.matches()) {
            getServletContext().getRequestDispatcher("/register-failed.jsp")
                    .forward(request, response);
        }

        Pattern surnameReg = Pattern.compile("[A-Za-z]{3,}");
        Matcher surnameMatch = surnameReg.matcher(surname);
        if (!surnameMatch.matches()) {
            getServletContext().getRequestDispatcher("/register-failed.jsp")
                    .forward(request, response);
        }

        if (!request.getParameter("password").equals(request.getParameter("re-password"))) {
            getServletContext().getRequestDispatcher("/register-failed.jsp")
                    .forward(request, response);
        } else {

            admin.setFirstName(name);
            admin.setLastName(surname);
            admin.setEmail(email);
            admin.setPassword(password);

            AdminDao adminDao = new AdminDao();
            adminDao.create(admin);

            response.sendRedirect("/login");
        }
    }
}
