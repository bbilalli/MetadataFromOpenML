package dao;

import entities.PredictiveAccuracy;

import java.util.List;

/**
 * Created by piotr on 05.04.2016.
 */
public interface IPredictiveAccuracyDao {
    PredictiveAccuracy selectById(int id);

    List<PredictiveAccuracy> selectAll();
}
