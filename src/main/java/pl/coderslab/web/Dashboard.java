package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.LastPlan;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession sess = request.getSession();


        int userId = (int) sess.getAttribute("id");
        //   sess.setAttribute("countRecipes", RecipeDao.countRecipes(userId));


        //   sess.setAttribute("countPlansQuery", PlanDao.countPlans(admin.getId()));


        System.out.println(PlanDao.countPlans((userId)));


        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);

      /*  if (admin == null) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
          //  request.setAttribute("countRecipes", RecipeDao.countRecipes(admin.getId()));

*/

        //       HttpSession sess = request.getSession();
/*
        int userId = ((Admin) sess.getAttribute("user")).getId();
        request.getSession().setAttribute("countRecipes", RecipeDao.countRecipes(userId));
        request.getSession().setAttribute("countPlansQuery", PlanDao.countPlansQuery(userId));

        getServletContext().getRequestDispatcher("/appDashboard.jsp").forward(request, response);
*/

    }
}