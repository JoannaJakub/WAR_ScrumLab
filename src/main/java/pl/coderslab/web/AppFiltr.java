package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/*")
public class AppFiltr implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request1 = (HttpServletRequest) request;

        HttpSession sess = request1.getSession();

        HttpServletResponse servlet = (HttpServletResponse) response;

        if (sess.getAttribute("enable") == null) {
            servlet.sendRedirect("/login.jsp");
        } else {
            servlet.sendRedirect("/dashboard.jsp");
        }
    }
}
