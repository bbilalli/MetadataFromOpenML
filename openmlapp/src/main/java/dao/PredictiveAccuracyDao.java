package dao;

import entities.PredictiveAccuracy;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 05.04.2016.
 */
public class PredictiveAccuracyDao implements IPredictiveAccuracyDao {
    @Override
    public PredictiveAccuracy selectById(int id) {
        PredictiveAccuracy predictiveAccuracy = new PredictiveAccuracy();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM predictive_accuracy WHERE did = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                predictiveAccuracy.setDid(resultSet.getInt("did"));
                predictiveAccuracy.setDataset(resultSet.getString("dataset"));
                predictiveAccuracy.setScore(resultSet.getDouble("score"));
                predictiveAccuracy.setAlgorithm(resultSet.getString("algorithm"));
            }

        } catch (Exception e) {

        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if(preparedStatement != null){
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(connection != null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return predictiveAccuracy;
    }

    @Override
    public List<PredictiveAccuracy> selectAll() {
        List<PredictiveAccuracy> list = new ArrayList<PredictiveAccuracy>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM predictive_accuracy LIMIT 10");
            resultSet = preparedStatement.executeQuery();
            PredictiveAccuracy pa = null;
            while (resultSet.next()) {
                pa = new PredictiveAccuracy();
                pa.setDid(resultSet.getInt("did"));
                pa.setDataset(resultSet.getString("dataset"));
                pa.setScore(resultSet.getDouble("score"));
                pa.setAlgorithm(resultSet.getString("algorithm"));

                list.add(pa);
            }

        } catch (Exception e) {

        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if(preparedStatement != null){
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if(connection != null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return list;
    }
}
