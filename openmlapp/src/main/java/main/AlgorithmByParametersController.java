package main;

import dao.AlgorithmsDao;
import dao.MetadataDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by marcin on 26.05.16.
 */
public class AlgorithmByParametersController extends AlgorithmByClassController {
    public AlgorithmByParametersController(){
        this.noAlgorithmWarning = "You must select an algorithm parameters!";
    }

    @Override
    protected void prepareAlgorithmListView(AlgorithmsDao algorithmsDao) throws SQLException, ClassNotFoundException {
        allAlgorithms = algorithmsDao.selectAlgorithmsByParameters();
    }

    @Override
    protected List<Map<String, Object>> getResults(String measure, String algorithm, List<String> metadata) throws SQLException, ClassNotFoundException {
        return new MetadataDao().selectMetadataByAlgorithmParams(algorithm,metadata,measure);
    }
}
