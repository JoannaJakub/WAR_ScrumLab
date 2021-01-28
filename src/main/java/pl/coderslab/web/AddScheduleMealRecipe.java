package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/AddRecipe")
public class AddScheduleMealRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();

        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> dayNames = dayNameDao.findAll();
        PlanDao planDao = new PlanDao();
        List <Plan> planList = planDao.read2(Integer.parseInt(String.valueOf(sess.getAttribute("id"))));
        request.setAttribute("plans" , planList);
        request.setAttribute("days" , dayNames);




        getServletContext().getRequestDispatcher("/AddScheduleMealRecipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
