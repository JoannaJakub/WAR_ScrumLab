package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.LastPlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        HttpSession sess = request.getSession();


        int userId = (int) sess.getAttribute("id");
        sess.setAttribute("countRecipes", RecipeDao.countRecipes(userId));
      //  System.out.println(RecipeDao.countRecipes(userId));
        sess.setAttribute("countPlansQuery", PlanDao.countPlans(userId));
       sess.setAttribute("recentPlanName", PlanDao.getLastPlanName(userId));
       sess.setAttribute("lastPlanDay", PlanDao.getLastPlanDay(userId));
      /* sess.setAttribute("lastPlanDay", PlanDao.getlastPlanDay(userId));
        System.out.println(PlanDao.getlastPlanDay(userId));*/







    //   System.out.println(PlanDao.getLastPlanName(userId));








//sess.setAttribute("weekdaysInPlan", PlanDao.lastPlan(userId, PlanDao.lastPlan(userId).getId())););

        //Map<String, List<LastPlan>> dailyRecipePlans = new HashMap<>();

/*
        LastPlan{dayName='poniedziałek',
            mealName='Śniadanie',
            recipeName='Przepis 4',
            recipeDescription='Opis przepisu 4',
            recipeId='0'}
*/


      //  System.out.println(PlanDao.lastPlan("dayName='poniedziałek'"));


        //
//        sess.setAttribute("day_name", lastPlanQUERY(userId));
//        System.out.println(PlanDao.countPlans(userId));

/*


        List<LastPlan> recentPlan = PlanDao.lastPlanQUERY(userId);

      Set<String> weekdaysInPlan = new HashSet<>();
        for (LastPlan LastPlan : recentPlan) {
            weekdaysInPlan.add(LastPlan.getDayName());
        }
        sess.setAttribute("weekdaysInPlan", weekdaysInPlan);
        sess.setAttribute("recentPlan", recentPlan);

        System.out.print( PlanDao.lastPlanQUERY(userId));
*/

        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);

    }
}
        /*sess.setAttribute("day", days);
        sess.setAttribute("recentPlan", recentPlan);
*/


   /* Map<String, ArrayList> recipePlanMap = planDao.finRecentUserPlan(id);
    String planName = (String) recipePlanMap.keySet().stream().toArray()[0];
        req.setAttribute("nameOfPlan", planName);
                List<RecipePlan> recipePlans = recipePlanMap.get(planName);

        req.setAttribute("recipePlans", recipePlans);
*/