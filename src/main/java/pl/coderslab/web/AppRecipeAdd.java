package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet ("/app/recipe/add")
public class AppRecipeAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        addRecipe(request, response);
        response.sendRedirect(request.getContextPath() + "/app/recipe");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/appRecipeAdd.jsp").forward(request, response);
    }

    public void addRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String description = request.getParameter("description");
        Calendar cal = Calendar.getInstance();
        String created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());

        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("id");

        Recipe recipe = new Recipe(name,ingredients, description,
                created, created, preparationTime, preparation, adminId);
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.create(recipe);
    }
}
