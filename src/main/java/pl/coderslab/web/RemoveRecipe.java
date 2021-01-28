package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/removeRecipe")
public class RemoveRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher("/appRemoveRecipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sess = request.getSession();
        int userId = (int) sess.getAttribute("id");

        RecipeDao recipePlanDao = new RecipeDao();
        recipePlanDao.delete(userId);
        response.sendRedirect("/app/recipe");
    }
}
