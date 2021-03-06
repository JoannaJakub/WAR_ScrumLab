package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;

import pl.coderslab.model.LastPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PlanDao {
//
    private static final String readQUERY = "SELECT * from scrumlab.plan where id = ?;";
    private static final String readQUERY2 = "SELECT * from scrumlab.plan where admin_id = ?;";
    private static final String findAllQUERY = "SELECT * FROM scrumlab.plan;";
    private static final String createQUERY = "INSERT INTO scrumlab.plan(id, name, description, created, admin_id) VALUES (?,?,?,?,?);";
    private static final String deleteQUERY = "DELETE FROM scrumlab.plan where id = ?;";
    private static final String updateQUERY = "UPDATE scrumlab.plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE	id = ?;";
    private static final String countPlansQuery = "SELECT count(admin_id) FROM scrumlab.plan WHERE admin_id=?;";
    private static final String lastPlanQUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description" +
            "    FROM `recipe_plan`" +
            "    JOIN day_name on day_name.id=day_name_id" +
            "    JOIN recipe on recipe.id=recipe_id WHERE" +
            "    recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)" +
            "    ORDER by day_name.display_order, recipe_plan.display_order";

    private static final String lastPlanNameQUERY ="SELECT plan.name AS plan_name FROM plan " +
            "WHERE id = (SELECT MAX(id) FROM plan WHERE admin_id = ?);";
    private static final String lastPlanDayQUERY = "SELECT day_name.name as day_name\n" +
            "FROM `recipe_plan`JOIN day_name on day_name.id=day_name_id\n" +
            "WHERE recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            " ORDER by day_name.display_order DESC limit 1;";



    public Plan read(int id) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(readQUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllQUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planAdd = new Plan();
                planAdd.setId(resultSet.getInt("id"));
                planAdd.setName(resultSet.getString("name"));
                planAdd.setDescription(resultSet.getString("description"));
                planAdd.setCreated(resultSet.getString("created"));
                planAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(createQUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, plan.getId());
            insertStm.setString(2, plan.getName());
            insertStm.setString(3, plan.getDescription());
            insertStm.setString(4, plan.getCreated());
            insertStm.setInt(5, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQUERY)) {
            statement.setInt(4, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(3, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int countPlans(int id) {
        int counter = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(countPlansQuery)) {
            statement.setInt(1, id);


            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    counter = resultSet.getInt("count(admin_id)");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;

    }
//
//    public static List<LastPlan> lastPlan(int id) {
//        List<LastPlan> planDetails = new ArrayList<>();
//        try (Connection connection = DbUtil.getConnection();
//             PreparedStatement statement = connection.prepareStatement(lastPlanQUERY);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                LastPlan tempPlanDetails = new LastPlan();
//                tempPlanDetails.setDayName(resultSet.getString("day_name"));
//                tempPlanDetails.setMealName(resultSet.getString("meal_name"));
//                tempPlanDetails.setRecipeName(resultSet.getString("recipe_name"));
//                tempPlanDetails.setRecipeDescription(resultSet.getString("recipe_description"));
//                tempPlanDetails.setRecipeId(resultSet.getInt("recipe_id"));
//                planDetails.add(tempPlanDetails);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return planDetails;
//
//       /* HashMap<String,List<LastPlan>> items = new HashMap<>();
//        planList.forEach(it->{
//            List<LastPlan> list = items.get(it.getDayName());
//            if(list==null){
//                list = new LinkedList();
//            }
//            list.add(it);
//            items.put(it.getDayName(),list);
//        });
//*/
//      //  return planDetails;
//
//
//    }
    public static String getLastPlanDay(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(lastPlanDayQUERY)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("day_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sfsf";
    }
    public static String getLastPlanName(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(lastPlanNameQUERY)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("plan_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Last Plan Name";
    }

    public static List<Plan> planList(int id) {

        List<Plan> planList = new ArrayList<>();
        List<Plan> reverseList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(readQUERY2)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Plan planAdd = new Plan();
                planAdd.setId(resultSet.getInt("id"));
                planAdd.setName(resultSet.getString("name"));
                planAdd.setDescription(resultSet.getString("description"));
                planList.add(planAdd);
            }

            for (int i = planList.size() - 1; i >= 0; i--) {
                if (i <= 1) {
                    reverseList.add(planList.get(i));
                } else {
                    reverseList.add(planList.get(i - 1));
                }

            }


        } catch (SQLException e) {
            return null;
        }
        return reverseList;

    }
    public List<Plan> FindAllFromAdminId(int id) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(readQUERY2)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Plan planAdd = new Plan();
                    planAdd.setId(resultSet.getInt("id"));
                    planAdd.setName(resultSet.getString("name"));
                    planAdd.setDescription(resultSet.getString("description"));
                    planAdd.setCreated(resultSet.getString("created"));
                    planAdd.setAdminId(resultSet.getInt("admin_id"));
                    planList.add(planAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }
    public void updateShort(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQUERY)) {
            statement.setInt(5, plan.getId());
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}










