package dao;

import util.ConnectionConfiguration;
import util.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 26.05.16.
 */
public class BaseDao {

    protected void executeQuery(String sqlQuery, ISqlResultHandler resultHandler) throws SQLException, ClassNotFoundException{
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        //List<String> measures = new ArrayList<>();
        //measures.add(""); // empty value

        try {
            connection = ConnectionConfiguration.getConnnection();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                resultHandler.handleResultRow(resultSet);
                //measures.add(resultSet.getString("measure"));
            }


        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected List<String> getStrings(String path) {
        return new FileUtils().getStrings(path);
    }

}
