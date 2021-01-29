package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/app/recipe/edit")
public class AppRecipeEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int recipeId = Integer.parseInt(request.getParameter("id"));
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = recipeDao.read(recipeId);
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/appRecipeEdit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe recipe = new Recipe();

        recipe.setAdmin_id(Integer.parseInt(request.getParameter("adminId")));
        recipe.setId(Integer.parseInt(request.getParameter("recipeId")));
        recipe.setName(request.getParameter("recipeName"));
        recipe.setIngredients(request.getParameter("ingredients"));
        recipe.setDescription(request.getParameter("description"));
        recipe.setCreated(request.getParameter("created"));
        Calendar cal = Calendar.getInstance();
        String updated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        recipe.setUpdated(updated);
        recipe.setPreparation_time(Integer.parseInt(request.getParameter("preparation_time")));
        recipe.setPreparation(request.getParameter("preparation"));

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.update(recipe);

        response.sendRedirect("/app/recipe");
    }
}
