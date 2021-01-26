package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;

import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String readQUERY = "SELECT * from scrumlab.plan where id = ?;";
    private static final String findAllQUERY = "SELECT * FROM scrumlab.plan;";
    private static final String createQUERY = "INSERT INTO scrumlab.plan(id, name, description, created, admin_id) VALUES (?,?,?,?,?);";
    private static final String deleteQUERY = "DELETE FROM scrumlab.plan where id = ?;";
    private static final String updateQUERY = "UPDATE scrumlab.plan SET name = ? , description = ?, created = ?, admin_id = ? WHERE	id = ?;";
    private static final String userPlanQuery = "SELECT * FROM scrumlab.plan WHERE admin_id=?;";


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

    public List<Plan> userPlanQuery(int id) {
        List<Plan> userPlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(userPlanQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan userPlan = new Plan();
                userPlan.setId(resultSet.getInt("id"));
                userPlan.setName(resultSet.getString("name"));
                userPlan.setDescription(resultSet.getString("description"));
                userPlan.setCreated(resultSet.getString("created"));
                userPlan.setAdminId(resultSet.getInt("admin_id"));
                userPlanList.add(userPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  userPlanList;

    }

}