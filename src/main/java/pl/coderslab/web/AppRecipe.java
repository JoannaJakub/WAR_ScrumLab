package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe")
public class AppRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        HttpSession sess = request.getSession();
        int adminId = (int) sess.getAttribute("id");

        RecipeDao recipeDao = new RecipeDao();
        sess.setAttribute("recipeList", recipeDao.readRecipesByAdminId(adminId));
        getServletContext().getRequestDispatcher("/appRecipe.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
