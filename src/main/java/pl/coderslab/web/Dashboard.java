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
        sess.setAttribute("countPlansQuery", PlanDao.countPlans(userId));
        sess.setAttribute("recentPlanName", PlanDao.getLastPlanName(userId));
        System.out.println(PlanDao.getLastPlanName((userId)));
//
//        sess.setAttribute("day_name", lastPlanQUERY(userId));
//        System.out.println(PlanDao.countPlans(userId));



        List<LastPlan> recentPlan = PlanDao.lastPlanQUERY(userId);

      Set<String> weekdaysInPlan = new HashSet<>();
        for (LastPlan LastPlan : recentPlan) {
            weekdaysInPlan.add(LastPlan.getDayName());
        }
        sess.setAttribute("weekdaysInPlan", weekdaysInPlan);
        sess.setAttribute("recentPlan", recentPlan);

        System.out.print( PlanDao.lastPlanQUERY(userId));

        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);

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