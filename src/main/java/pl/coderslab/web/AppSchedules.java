package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan")
public class AppSchedules extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

HttpSession sess = request.getSession();

List<Plan> plan = PlanDao.planList(Integer.parseInt(String.valueOf(sess.getAttribute("id"))));

request.setAttribute("PlanList" , plan);
        getServletContext().getRequestDispatcher("/appSchedule.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
