package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQueryFromFile("sql/find_max_projects_client.sql"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                result.add(new MaxProjectCountClient(projectCount, name));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public LongestProject findLongestProject() {
        LongestProject result = null;
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQueryFromFile("sql/find_longest_projects.sql"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                result = new LongestProject(projectCount, name);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQueryFromFile("sql/find_max_salary_workers.sql"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int workersCount = resultSet.getInt("project_count");
                result.add(new MaxSalaryWorker(workersCount, name));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<YoungestOldestWorker> YoungestOldestWorker() {
        List<YoungestOldestWorker> result = new ArrayList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getQueryFromFile("sql/find_youngest_eldest_workers.sql"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Date Birthday = resultSet.getDate("BirthDay");
                result.add(new YoungestOldestWorker(Birthday, name));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getQueryFromFile(String filePath) throws IOException {
        StringBuilder query = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                query.append(line).append("\n");
            }
        }
        return query.toString();
    }
}