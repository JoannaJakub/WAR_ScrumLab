package pl.coderslab.dao;

import pl.coderslab.model.Recipe_Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe_PlanDAO {

    private static final String ADDQUERY = "INSERT INTO scrumlab.recipe_plan(id,recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?);";
    private static final String COUNT_RECIPES_IN_PLAN_QUERY = "SELECT COUNT(recipe_id) FROM scrumlab.recipe_plan WHERE recipe_id = ?;";

    public int countRecipesInPlan (int recipeId){
        int counter = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_RECIPES_IN_PLAN_QUERY)) {
            statement.setInt(1, recipeId);


            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    counter = resultSet.getInt("count(recipe_id)");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;

    }

    public Recipe_Plan create (Recipe_Plan recipe_plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(ADDQUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipe_plan.getId());
            insertStm.setInt(2, recipe_plan.getRecipe_id());
            insertStm.setString(3, recipe_plan.getMeal_name());
            insertStm.setInt(4, recipe_plan.getDisplay_order());
            insertStm.setInt(5, recipe_plan.getDay_name_id());
            insertStm.setInt(6, recipe_plan.getPlan_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe_plan.setId(generatedKeys.getInt(1));
                    return recipe_plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
