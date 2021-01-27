package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet ("/app/plan/add")
public class AppAddSchedules extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setUpPlan(request, response);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/appAddSchedules.jsp").forward(request, response);
    }

    public void setUpPlan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("planName");
        String description = request.getParameter("planDesc");
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        int adminId = (int) session.getAttribute("id");

        Plan plan = new Plan(0, name, date,description, adminId);
        PlanDao planDao = new PlanDao();
        planDao.create(plan);
    }
}
