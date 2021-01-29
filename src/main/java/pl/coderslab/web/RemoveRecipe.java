
package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.Recipe_PlanDAO;

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
        int recipeId = Integer.parseInt(request.getParameter("id"));

        Recipe_PlanDAO recipe_planDAO = new Recipe_PlanDAO();
        if (recipe_planDAO.countRecipesInPlan(recipeId) == 0) {
            getServletContext().getRequestDispatcher("/appRemoveRecipe.jsp").forward(request, response);
        } else { getServletContext().getRequestDispatcher("/appRemoveRecipeError.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int recipeId = Integer.parseInt(request.getParameter("id"));
        System.out.println(recipeId);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.delete(recipeId);

        response.sendRedirect("/app/recipe");
    }
}