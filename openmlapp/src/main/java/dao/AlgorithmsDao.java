package dao;

import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 26.05.16.
 */

public class AlgorithmsDao extends BaseDao{


    public List<String> selectAlgorithmsByClass() throws SQLException, ClassNotFoundException {
        List<String> algorithms = new ArrayList<>();
        this.executeQuery("SELECT * FROM algorithm_classes",(result)->{
            algorithms.add(result.getString("algorithm_class"));
        });
        return algorithms;
    }

    public List<String> selectAlgorithmsByVersion() throws SQLException, ClassNotFoundException {
        List<String> algorithms = new ArrayList<>();
        this.executeQuery("SELECT * FROM algorithm_versions",(result)->{
            algorithms.add(result.getString("algorithm"));
        });
        return algorithms;
    }

    public List<String> selectAlgorithmsByParameters() throws SQLException, ClassNotFoundException {
        List<String> algorithms = new ArrayList<>();
        this.executeQuery("select concat(algorithm,'#', coalesce(algorithm_parameters,'<empty>')) as algorithm_parameters from algorithm_version_with_parameters",(result)->{
            algorithms.add(result.getString("algorithm_parameters"));
        });
        return algorithms;
    }

}
