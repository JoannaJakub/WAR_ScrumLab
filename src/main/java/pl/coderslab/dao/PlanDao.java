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


    // Kom 1 : Nie jestem w stanie stwierdzic , czy dane bedą ukrywane czy trzeba QUERY zmienić ale aktualnie zakładam wariant z ukrywaniem (po co użytkownikowi wiedza na temat id admina).
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

    // Kom 2 : Ten sam przypadek co wyżej tutaj
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

    // Kom 3: Nie jestem tu pewny odnośnie created oraz superAdmin, ponieważ makieta ma tam tylko pola name oraz description, a przeciez nie kazdy tworzący plan będzie adminem.
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
// Kom 4 : Przypadłość z Kom 1,2 - nie będziemy chcieli domyślnie zmieniać admin_id gdy jesteśmy użytkownikami
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
}

// Po zweryfikowaniu i pózniejszym zaakceptowaniu proszę o usunięcię wszystkich komentarzy!!!
// W razie jakichkolwiek błędów przypominam o wrzuceniu kafelka , którego dotyczy kod
// Po wykonanym code review napisz na slacku (czy to na priv czy poprzez oznaczenie na czacie) o wyniku owage code review.