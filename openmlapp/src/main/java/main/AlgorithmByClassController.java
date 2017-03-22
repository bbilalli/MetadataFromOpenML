package main;

import dao.AlgorithmsDao;
import dao.MetadataDao;
import dao.MiscellaneousDao;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.dialog.ProgressDialog;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by marcin on 26.05.16.
 */
public class AlgorithmByClassController {

    protected String noMeasuresWarning = "You must select a measure!";
    protected String noAlgorithmWarning = "You must select an algorithm class!";
    protected String noMetadataWarning = "You must select at least one meta attribute!";
    private Main main;



    @FXML
    ListView<String> algorithmListView;

    @FXML ListView<String> measureListView;

    @FXML ListView<String> availableMetadataListView;

    @FXML ListView<String> selectedMetadataListView;

    @FXML TextField searchMetadataTextField;

    @FXML
    TextField searchTextField;

    protected List<String> allAlgorithms;

    protected List<String> allMetadata;


    public void init(){
        AlgorithmsDao algorithmsDao = new AlgorithmsDao();
        MiscellaneousDao miscellaneousDao = new MiscellaneousDao();
        try {
            prepareAlgorithmListView(algorithmsDao);
            algorithmListView.setItems(FXCollections.observableList(allAlgorithms));
            measureListView.setItems(FXCollections.observableList(miscellaneousDao.selectAvailableMeasures()));

            allMetadata = miscellaneousDao.selectAvailableMetadata();
            availableMetadataListView.setItems(FXCollections.observableList(allMetadata.stream().sorted().collect(Collectors.toList())));

            selectedMetadataListView.setItems(FXCollections.observableList(miscellaneousDao.selectMandatoryMetadata()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //algorithmListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    protected void prepareAlgorithmListView(AlgorithmsDao algorithmsDao) throws SQLException, ClassNotFoundException {
        allAlgorithms = algorithmsDao.selectAlgorithmsByClass();

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void searchTextFieldKeyUp(KeyEvent args){
        String searchText = searchTextField.getText().toLowerCase();
        List<String> filtered = null;
        if(!searchText.isEmpty()){
            filtered = this.allAlgorithms.stream().filter((alg)->alg.toLowerCase().contains(searchText)).collect(Collectors.toList());
        }
        else{
            filtered = allAlgorithms;
        }

        this.algorithmListView.getSelectionModel().clearSelection();
        this.algorithmListView.setItems(FXCollections.observableList(filtered));

    }

    public void searchMetadataTextFieldKeyUp(KeyEvent args){
        String searchText = searchMetadataTextField.getText().toLowerCase();
        List<String> filtered = null;
        if(!searchText.isEmpty()){
            filtered = this.allMetadata.stream().filter((alg)->alg.toLowerCase().contains(searchText)).collect(Collectors.toList());
        }
        else{
            filtered = allMetadata;
        }

        this.availableMetadataListView.getSelectionModel().clearSelection();
        this.availableMetadataListView.setItems(FXCollections.observableList(filtered.stream().sorted().collect(Collectors.toList())));

    }

    public void availableMetadataMouseClicked(MouseEvent args){
        if(args.getClickCount() == 2){
            String selected = availableMetadataListView.getSelectionModel().getSelectedItem();
            addMetadata(selected);
        }
    }

    protected void addMetadata(String selected) {
        if(!selectedMetadataListView.getItems().contains(selected)){
            selectedMetadataListView.getItems().add(selected);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("You have already selected this property");
            alert.setContentText("");

            alert.showAndWait();
        }
        availableMetadataListView.getSelectionModel().clearSelection();
    }

    public void seletecMetadataMouseClicked(MouseEvent args){
        if(args.getClickCount() == 2){
            String selected = selectedMetadataListView.getSelectionModel().getSelectedItem();
            removeMetadata(selected);
        }
    }

    protected void removeMetadata(String selected) {
        selectedMetadataListView.getItems().remove(selected);
        selectedMetadataListView.getSelectionModel().clearSelection();
    }

    public void buttonAddClicked(ActionEvent args){
        if(availableMetadataListView.getSelectionModel().getSelectedItems().toArray().length == 1){
            addMetadata(availableMetadataListView.getSelectionModel().getSelectedItem());
        }
    }

    public void buttonRemoveClicked(ActionEvent args){
        if(selectedMetadataListView.getSelectionModel().getSelectedItems().toArray().length == 1){
            removeMetadata(selectedMetadataListView.getSelectionModel().getSelectedItem());
        }
    }

    public void buttonClearClicked(ActionEvent args){
        selectedMetadataListView.getItems().clear();
    }

    public void buttonResetClicked(ActionEvent args){
        selectedMetadataListView.setItems(FXCollections.observableList(new MiscellaneousDao().selectMandatoryMetadata()));
    }

    public void buttonGenerateMetadataClicked(ActionEvent args){
        String measure = measureListView.getSelectionModel().getSelectedItem();
        if(measure == null || measure.isEmpty()){
            showWarning(noMeasuresWarning);
            return;
        }

        String algorithmClass = algorithmListView.getSelectionModel().getSelectedItem();

        if(algorithmClass == null || algorithmClass.isEmpty()){
            showWarning(noAlgorithmWarning);
            return;
        }

        List<String> metadata = selectedMetadataListView.getItems().stream().collect(Collectors.toList());
        if(metadata == null || metadata.size() == 0){
            showWarning(noMetadataWarning);
            return;
        }

        List<String> metadataAll = new ArrayList<>(metadata);
        metadataAll.add("name");

        final String[] methodsToCheck = new String[]{"weka.NaiveBayes", "weka.BayesianLogisticRegression", "weka.IBk", "weka.J48", "weka.JRip"};

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call()
                            throws InterruptedException {
                        try {
                            updateMessage("Fetching data...");
                            List<Map<String,Object>> selectedMetadata = getResults(measure, algorithmClass, metadata);

                            AlgorithmByClassController.this.main.setCurrentResults(selectedMetadata);
                            AlgorithmByClassController.this.main.setCurrentMetaAttributes(metadata);
                            Platform.runLater(AlgorithmByClassController.this::selectResultsTab);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Platform.runLater(() -> showWarning(e.getMessage()));
                        }
                        return null;
                    }
                };
            }
        };

        ProgressDialog progressDialog = new ProgressDialog(service);
        progressDialog.setTitle("Work in progress.");
        progressDialog.setHeaderText("SQL Data Fetch.");
        service.start();
    }

    protected List<Map<String, Object>> getResults(String measure, String algorithm, List<String> metadata) throws SQLException, ClassNotFoundException {
        return new MetadataDao().selectMetadataByAlgorithmClass(algorithm,metadata,measure);
    }


    protected void selectResultsTab() {
        Tab tab = this.mainTabs.getTabs().stream().filter(x->x.getId() != null && x.getId().equals("resultTab")).collect(Collectors.toList()).get(0);
        this.mainTabs.getSelectionModel().select(tab);
    }

    public void showWarning(String header, String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(text);

        alert.showAndWait();
    }

    public void showWarning(String header){
        showWarning(header, "");
    }


    protected TabPane mainTabs;
    public TabPane getMainTabs() {
        return mainTabs;
    }

    public void setMainTabs(TabPane mainTabs) {
        this.mainTabs = mainTabs;
    }
}
