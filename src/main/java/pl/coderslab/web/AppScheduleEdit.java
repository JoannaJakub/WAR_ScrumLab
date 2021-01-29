package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/plan/edit")
public class AppScheduleEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(Integer.parseInt(request.getParameter("plan_id")));
        request.setAttribute("name" , plan.getName());
        request.setAttribute("description" , plan.getDescription());
        getServletContext().getRequestDispatcher("/AppScheduleEdit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Plan plan = new Plan();
HttpSession sess = request.getSession();
plan.setId(Integer.parseInt(request.getParameter("plan_id")));
plan.setName(String.valueOf(request.getParameter("planName")));
plan.setDescription(String.valueOf(request.getParameter("planDescription")));
plan.setAdminId((Integer) sess.getAttribute("id"));
PlanDao planDao = new PlanDao();
planDao.updateShort(plan);
response.sendRedirect("/app/plan");
    }
}
