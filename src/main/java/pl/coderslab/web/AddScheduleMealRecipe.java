package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.Recipe_PlanDAO;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.Recipe_Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/plan/add")
public class AddScheduleMealRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayNames = dayNameDao.findAll();
        PlanDao planDao = new PlanDao();
        List<Plan> planList = planDao.read2(Integer.parseInt(String.valueOf(sess.getAttribute("id"))));
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipeList = recipeDao.read2(Integer.parseInt(String.valueOf(sess.getAttribute("id"))));
        request.setAttribute("plans", planList);
        request.setAttribute("days", dayNames);
        request.setAttribute("recipies", recipeList);


        getServletContext().getRequestDispatcher("/AddScheduleMealRecipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe_PlanDAO recipe_planDAO = new Recipe_PlanDAO();
        Recipe_Plan recipe_plan = new Recipe_Plan();
        recipe_plan.setRecipe_id(Integer.parseInt(request.getParameter("choosePlan")));
        recipe_plan.setMeal_name(request.getParameter("name"));
        recipe_plan.setDisplay_order(Integer.parseInt(request.getParameter("number")));
        recipe_plan.setPlan_id(Integer.parseInt(request.getParameter("recipie")));
        recipe_plan.setDay_name_id(Integer.parseInt(request.getParameter("day")));
        recipe_planDAO.create(recipe_plan);
        response.sendRedirect("/app/plan");
    }
}
