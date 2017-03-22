package dao;

import entities.FullMetaData;
import util.ConnectionConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 06.04.2016.
 */
public class FullMetaDataDao implements IFullMetaDataDao {

    @Override

    public List<FullMetaData> selectAll(String measure, String algorithm) throws SQLException, ClassNotFoundException {

        List<FullMetaData> list = new ArrayList<FullMetaData>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if(measure == null || measure == ""){
            measure = "%";
        }

        if(algorithm == null || algorithm == ""){
            algorithm = "%";
        }

        try {
            String statement =
                    "SELECT *" +
                    "FROM dataset_metadata meta\n" +
                    "  JOIN\n" +
                    "  (\n" +
                    "    SELECT\n" +
                    "      dm.*,\n" +
                    "      (SELECT COUNT(*) AS cx\n" +
                    "       FROM\n" +
                    "         dataset_all_measures inrx\n" +
                    "       WHERE\n" +
                    "         inrx.did = dm.did\n" +
                    "         AND inrx.measure = dm.measure\n" +
                    "         AND inrx.algorithm = dm.algorithm\n" +
                    "         AND inrx.score < dm.score) AS cnt\n" +
                    "    FROM\n" +
                    "      dataset_all_measures AS dm\n" +
                    "    WHERE\n" +
                    "      dm.measure LIKE ?\n" +
                    "      AND dm.algorithm LIKE ?\n" +
                    "  ) nested\n" +
                    "    ON nested.did = meta.did\n" +
                    "ORDER BY meta.did, measure, algorithm, score\n";

            connection = ConnectionConfiguration.getConnnection();
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, measure);
            preparedStatement.setString(2, algorithm);
            resultSet = preparedStatement.executeQuery();
            FullMetaData fmd = null;
            while (resultSet.next()) {
                fmd = new FullMetaData();
                fmd.setMeta_did(resultSet.getInt("meta.did"));
                fmd.setName(resultSet.getString("name"));
                fmd.setDefault_target_attribute(resultSet.getString("default_target_attribute"));
                fmd.setUrl(resultSet.getString("url"));
                fmd.setClassCount(resultSet.getDouble("ClassCount"));
                fmd.setClassEntropy(resultSet.getDouble("ClassEntropy"));
                fmd.setDecisionStumpAUC(resultSet.getDouble("DecisionStumpAUC"));
                fmd.setDecisionStumpErrRate(resultSet.getDouble("DecisionStumpErrRate"));
                fmd.setDecisionStumpKappa(resultSet.getDouble("DecisionStumpKappa"));
                fmd.setDefaultAccuracy(resultSet.getDouble("DefaultAccuracy"));
                fmd.setDimensionality(resultSet.getDouble("Dimensionality"));
                fmd.setEquivalentNumberOfAtts(resultSet.getDouble("EquivalentNumberOfAtts"));
                fmd.setHoeffdingAdwin_changes(resultSet.getDouble("HoeffdingAdwin.changes"));
                fmd.setHoeffdingAdwin_warnings(resultSet.getDouble("HoeffdingAdwin.warnings"));
                fmd.setIncompleteInstanceCount(resultSet.getDouble("IncompleteInstanceCount"));
                fmd.setInstanceCount(resultSet.getDouble("InstanceCount"));
                fmd.setJ48_00001_AUC(resultSet.getDouble("J48.00001.AUC"));
                fmd.setJ48_00001_ErrRate(resultSet.getDouble("J48.00001.ErrRate"));
                fmd.setJ48_00001_Kappa(resultSet.getDouble("J48.00001.Kappa"));
                fmd.setJ48_0001_AUC(resultSet.getDouble("J48.0001.AUC"));
                fmd.setJ48_0001_ErrRate(resultSet.getDouble("J48.0001.ErrRate"));
                fmd.setJ48_0001_Kappa(resultSet.getDouble("J48.001.Kappa"));
                fmd.setJ48_001_AUC(resultSet.getDouble("J48.001.AUC"));
                fmd.setJ48_001_ErrRate(resultSet.getDouble("J48.001.ErrRate"));
                fmd.setJ48_001_Kappa(resultSet.getDouble("J48.001.Kappa"));
                fmd.setJRipAUC(resultSet.getDouble("JRipAUC"));
                fmd.setJRipErrRate(resultSet.getDouble("JRipErrRate"));
                fmd.setJRipKappa(resultSet.getDouble("JRipKappa"));
                fmd.setMajorityClassSize(resultSet.getInt("MajorityClassSize"));
                fmd.setMaxNominalAttDistinctValues(resultSet.getDouble("MaxNominalAttDistinctValues"));
                fmd.setMeanAttributeEntropy(resultSet.getDouble("MeanAttributeEntropy"));
                fmd.setMeanKurtosisOfNumericAtts(resultSet.getDouble("MeanKurtosisOfNumericAtts"));
                fmd.setMeanMeansOfNumericAtts(resultSet.getDouble("MeanMeansOfNumericAtts"));
                fmd.setMeanMutualInformation(resultSet.getDouble("MeanMutualInformation"));
                fmd.setMeanNominalAttDistinctValues(resultSet.getDouble("MeanNominalAttDistinctValues"));
                fmd.setMeanSkewnessOfNumericAtts(resultSet.getDouble("MeanSkewnessOfNumericAtts"));
                fmd.setMeanStdDevOfNumericAtts(resultSet.getDouble("MeanStdDevOfNumericAtts"));
                fmd.setMinNominalAttDistinctValues(resultSet.getDouble("MinNominalAttDistinctValues"));
                fmd.setMinorityClassSize(resultSet.getInt("MinorityClassSize"));
                fmd.setNBTreeAUC(resultSet.getDouble("NBTreeAUC"));
                fmd.setNBTreeErrRate(resultSet.getDouble("NBTreeErrRate"));
                fmd.setNBTreeKappa(resultSet.getDouble("NBTreeKappa"));
                fmd.setNaiveBayesAUC(resultSet.getDouble("NaiveBayesAUC"));
                fmd.setNaiveBayesAdwin_changes(resultSet.getDouble("NaiveBayesAdwin.changes"));
                fmd.setNaiveBayesAdwin_warnings(resultSet.getDouble("NaiveBayesAdwin.warnings"));
                fmd.setNaiveBayesDdm_changes(resultSet.getDouble("NaiveBayesDdm.changes"));
                fmd.setNaiveBayesDdm_warnings(resultSet.getDouble("NaiveBayesDdm.warnings"));
                fmd.setNaiveBayesErrRate(resultSet.getDouble("NaiveBayesErrRate"));
                fmd.setNaiveBayesKappa(resultSet.getDouble("NaiveBayesKappa"));
                fmd.setNegativePercentage(resultSet.getDouble("NegativePercentage"));
                fmd.setNoiseToSignalRatio(resultSet.getDouble("NoiseToSignalRatio"));
                fmd.setNumAttributes(resultSet.getDouble("NumAttributes"));
                fmd.setNumBinaryAtts(resultSet.getDouble("NumBinaryAtts"));
                fmd.setNumMissingValues(resultSet.getDouble("NumMissingValues"));
                fmd.setNumNominalAtts(resultSet.getDouble("NumNominalAtts"));
                fmd.setNumNumericAtts(resultSet.getDouble("NumNumericAtts"));
                fmd.setNumberOfClasses(resultSet.getInt("NumberOfClasses"));
                fmd.setNumberOfInstances(resultSet.getInt("NumberOfInstances"));
                fmd.setNumberOfInstancesWithMissingValues(resultSet.getInt("NumberOfInstancesWithMissingValues"));
                fmd.setNumberOfMissingValues(resultSet.getInt("NumberOfMissingValues"));
                fmd.setNumberOfNumericFeatures(resultSet.getInt("NumberOfNumericFeatures"));
                fmd.setNumberOfSymbolicFeatures(resultSet.getInt("NumberOfSymbolicFeatures"));
                fmd.setPercentageOfBinaryAtts(resultSet.getDouble("PercentageOfBinaryAtts"));
                fmd.setPercentageOfMissingValues(resultSet.getDouble("PercentageOfMissingValues"));
                fmd.setPercentageOfNominalAtts(resultSet.getDouble("PercentageOfNominalAtts"));
                fmd.setPercentageOfNumericAtts(resultSet.getDouble("PercentageOfNumericAtts"));
                fmd.setPositivePercentage(resultSet.getDouble("PositivePercentage"));
                fmd.setREPTreeDepth1AUC(resultSet.getDouble("REPTreeDepth1AUC"));
                fmd.setREPTreeDepth1ErrRate(resultSet.getDouble("REPTreeDepth1ErrRate"));
                fmd.setREPTreeDepth1Kappa(resultSet.getDouble("REPTreeDepth1Kappa"));
                fmd.setREPTreeDepth2AUC(resultSet.getDouble("REPTreeDepth2AUC"));
                fmd.setREPTreeDepth2ErrRate(resultSet.getDouble("REPTreeDepth2Kappa"));
                fmd.setREPTreeDepth2Kappa(resultSet.getDouble("REPTreeDepth2ErrRate"));
                fmd.setREPTreeDepth3AUC(resultSet.getDouble("REPTreeDepth3AUC"));
                fmd.setREPTreeDepth3ErrRate(resultSet.getDouble("REPTreeDepth3ErrRate"));
                fmd.setREPTreeDepth3Kappa(resultSet.getDouble("REPTreeDepth3AUC"));
                fmd.setRandomTreeDepth1AUC(resultSet.getDouble("RandomTreeDepth1AUC"));
                fmd.setRandomTreeDepth1ErrRate(resultSet.getDouble("RandomTreeDepth1ErrRate"));
                fmd.setRandomTreeDepth1Kappa(resultSet.getDouble("RandomTreeDepth1Kappa"));
                fmd.setRandomTreeDepth2AUC(resultSet.getDouble("RandomTreeDepth2AUC"));
                fmd.setRandomTreeDepth2ErrRate(resultSet.getDouble("RandomTreeDepth2ErrRate"));
                fmd.setRandomTreeDepth2Kappa(resultSet.getDouble("RandomTreeDepth2Kappa"));
                fmd.setRandomTreeDepth3AUC(resultSet.getDouble("RandomTreeDepth3AUC"));
                fmd.setRandomTreeDepth3ErrRate(resultSet.getDouble("RandomTreeDepth3ErrRate"));
                fmd.setRandomTreeDepth3Kappa(resultSet.getDouble("RandomTreeDepth3Kappa"));
                fmd.setSVMe1AUC(resultSet.getDouble("SVMe1AUC"));
                fmd.setSVMe1ErrRate(resultSet.getDouble("SVMe1ErrRate"));
                fmd.setSVMe1Kappa(resultSet.getDouble("SVMe1Kappa"));
                fmd.setSVMe2AUC(resultSet.getDouble("SVMe2AUC"));
                fmd.setSVMe2ErrRate(resultSet.getDouble("SVMe2ErrRate"));
                fmd.setSVMe2Kappa(resultSet.getDouble("SVMe2Kappa"));
                fmd.setSVMe3AUC(resultSet.getDouble("SVMe3AUC"));
                fmd.setSVMe3ErrRate(resultSet.getDouble("SVMe3ErrRate"));
                fmd.setSVMe3Kappa(resultSet.getDouble("SVMe3Kappa"));
                fmd.setSimpleLogisticAUC(resultSet.getDouble("SimpleLogisticAUC"));
                fmd.setSimpleLogisticErrRate(resultSet.getDouble("SimpleLogisticErrRate"));
                fmd.setSimpleLogisticKappa(resultSet.getDouble("SimpleLogisticKappa"));
                fmd.setStdvNominalAttDistinctValues(resultSet.getDouble("StdvNominalAttDistinctValues"));
                fmd.setkNN_1NAUC(resultSet.getDouble("kNN_1NAUC"));
                fmd.setkNN_1NErrRate(resultSet.getDouble("kNN_1NErrRate"));
                fmd.setkNN_1NKappa(resultSet.getDouble("kNN_1NKappa"));
                fmd.setkNN_2NAUC(resultSet.getDouble("kNN_2NAUC"));
                fmd.setkNN_2NErrRate(resultSet.getDouble("kNN_2NErrRate"));
                fmd.setkNN_2NKappa(resultSet.getDouble("kNN_2NKappa"));
                fmd.setkNN_3NAUC(resultSet.getDouble("kNN_3NAUC"));
                fmd.setkNN_3NErrRate(resultSet.getDouble("kNN_3NErrRate"));
                fmd.setkNN_3NKappa(resultSet.getDouble("kNN_3NKappa"));
                fmd.setNested_did(resultSet.getInt("nested.did"));
                fmd.setDataset(resultSet.getString("dataset"));
                fmd.setMeasure(resultSet.getString("measure"));
                fmd.setScore(resultSet.getDouble("score"));
                fmd.setAlgorithm(resultSet.getString("algorithm"));
                fmd.setCnt(resultSet.getInt("cnt"));

                list.add(fmd);
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
        return list;
    }

    @Override

    public List<String> selectAlgorithms() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<String> algorithms = new ArrayList<>();
        algorithms.add(""); // empty value

        try {
            connection = ConnectionConfiguration.getConnnection();
            String statement = "SELECT * FROM available_algorithms";
            preparedStatement = connection.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                algorithms.add(resultSet.getString("name"));
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
        return algorithms;
    }

    @Override

    public List<String> selectMeasures() throws SQLException, ClassNotFoundException {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        List<String> measures = new ArrayList<>();
        measures.add(""); // empty value

        try {
            connection = ConnectionConfiguration.getConnnection();
            String statement = "SELECT * FROM available_measures";
            preparedStatement = connection.prepareStatement(statement);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                measures.add(resultSet.getString("measure"));
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
        return measures;
    }
}
