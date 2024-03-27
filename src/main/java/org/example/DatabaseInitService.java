package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class DatabaseInitService {

        public static void main(String[] args) {
            String sqlFilePath = "sql/init_db.sql";
            StringBuilder sqlCommands = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(sqlFilePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sqlCommands.append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            try (Connection connection = Database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sqlCommands.toString())) {

                preparedStatement.execute();

                System.out.println("Database has been created");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

