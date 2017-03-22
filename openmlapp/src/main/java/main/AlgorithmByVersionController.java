package main;

import dao.AlgorithmsDao;
import dao.MetadataDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by marcin on 26.05.16.
 */
public class AlgorithmByVersionController extends AlgorithmByClassController {

    public AlgorithmByVersionController()
    {
        this.noAlgorithmWarning = "You must select an algorithm version!";
    }

    @Override
    protected void prepareAlgorithmListView(AlgorithmsDao algorithmsDao) throws SQLException, ClassNotFoundException {
        allAlgorithms = algorithmsDao.selectAlgorithmsByVersion();
    }

    @Override
    protected List<Map<String, Object>> getResults(String measure, String algorithm, List<String> metadata) throws SQLException, ClassNotFoundException {
        return new MetadataDao().selectMetadataByAlgorithmVersion(algorithm,metadata,measure);
    }
}
