package dao;

import entities.FullMetaData;


import java.sql.SQLException;

import java.util.List;

/**
 * Created by piotr on 06.04.2016.
 */
public interface IFullMetaDataDao {

    List<FullMetaData> selectAll(String measure, String algorithm) throws SQLException, ClassNotFoundException;

    List<String> selectAlgorithms() throws SQLException, ClassNotFoundException;

    List<String> selectMeasures() throws SQLException, ClassNotFoundException;


}
