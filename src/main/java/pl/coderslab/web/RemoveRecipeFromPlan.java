package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/removeRecipeFromPlan")
public class RemoveRecipeFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher("/appRemoveRecipeFromPlan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sess = request.getSession();
        int userId = (int) sess.getAttribute("id");

        RecipeDao recipeRecipeDao = new RecipeDao();
        recipeRecipeDao.delete(userId);
        response.sendRedirect("/app/recipe/details");
    }
}
