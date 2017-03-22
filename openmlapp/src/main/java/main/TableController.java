package main;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import dao.FullMetaDataDao;
import dao.PredictiveAccuracyDao;
import entities.FullMetaData;
import entities.PredictiveAccuracy;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import sun.security.ssl.Debug;
import util.ConnectionConfiguration;
import util.WekaRunner;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by piotr on 05.04.2016.
 */
public class TableController {
    public TableView table;
    public TableColumn did;
    public TableColumn dataset;
    public TableColumn measure;
    public TableColumn score;
    public TableColumn algorithm;
    public ChoiceBox<String> measures;
    public ChoiceBox<String> algorithms;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void init() {
        try {
            FullMetaDataDao fmdd = new FullMetaDataDao();
            List<String> algorithms = fmdd.selectAlgorithms();
            List<String> measures = fmdd.selectMeasures();

            this.algorithms.setItems(FXCollections.observableList(algorithms));
            this.measures.setItems(FXCollections.observableList(measures));
        } catch (Exception ex) {
            if (ex.getCause() instanceof ConnectException) {
                Platform.runLater(() ->  {
                    Notifications.create()
                            .title("Connection problem!")
                            .text("Cannot connect to Database.\nURL: " + ConnectionConfiguration.CONNECTION_URL)
                            .showError();
                });
            } else {
                ex.printStackTrace();
            }
        }


    }

    public void updateTableView(List<FullMetaData> list) {
        PredictiveAccuracyDao pad = new PredictiveAccuracyDao();
        ObservableList<FullMetaData> observableList = FXCollections.observableList(list);

        did.setCellValueFactory(new PropertyValueFactory<PredictiveAccuracy, Integer>("meta_did"));
        dataset.setCellValueFactory(new PropertyValueFactory<PredictiveAccuracy, String>("dataset"));
        measure.setCellValueFactory(new PropertyValueFactory<PredictiveAccuracy, String>("measure"));
        score.setCellValueFactory(new PropertyValueFactory<PredictiveAccuracy, Double>("score"));
        algorithm.setCellValueFactory(new PropertyValueFactory<PredictiveAccuracy, String>("algorithm"));

        table.setItems(observableList);
    }

    public void loadFullMetaData(Event event){
        FullMetaDataDao fmdd = new FullMetaDataDao();
        List<FullMetaData> fullList;
        try {
            fullList = fmdd.selectAll((String) measures.getValue(), (String) algorithms.getValue());
        } catch (Exception ex){
            Notifications.create()
                    .title("loadFullMetaData error!")
                    .text(ex.getMessage())
                    .showError();
            return;
        }
        List<FullMetaData> meanList = new ArrayList<>();
        FullMetaData previous = null;
        List<Double> scores = new ArrayList<>();
        double mean = 0.0;

        for (FullMetaData fmd : fullList) {
            if(previous == null){
                previous = fmd;
            }

            if( previous.getMeta_did() != fmd.getMeta_did() ||
                !previous.getAlgorithm().equals(fmd.getAlgorithm()) ||
                !previous.getMeasure().equals(fmd.getMeasure())){
                if(scores != null){
                    mean = calculateMean(scores);
                    previous.setScore(mean);
                    meanList.add(previous);
                }
                scores = new ArrayList<>();
                previous = fmd;
            }

            scores.add(fmd.getScore());
        }

        if(scores != null){
            mean = calculateMean(scores);
            previous.setScore(mean);
            meanList.add(previous);
        }
        updateTableView(meanList);
        writeToCsv(meanList);
    }

    private double calculateMean(List<Double> scores){
        int size = scores.size();
        int idx = scores.size()/2;

        if(size % 2 == 0){
            return (scores.get(idx) + scores.get(idx-1)) / 2;
        }

        return scores.get(idx);
    }

    private void writeToCsv(List<FullMetaData> list){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.csv", "UTF-8");
            for (FullMetaData fmd : list) {
                writer.println(fmd.toString());
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }

    }

}
