package main;

import dao.AlgorithmsDao;
import dao.MetadataDao;
import dao.MiscellaneousDao;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.ProgressDialog;
import util.FileUtils;
import util.MetadataExtractor;
import util.UIUtils;
import util.WekaRunner;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by marcin on 26.05.16.
 */
public class UserDatasetController extends AlgorithmByClassController{

    @FXML
    public Label labelSelectedDirectory;

    private SimpleObjectProperty<File> testFile = new SimpleObjectProperty<>(),
                                       selectedDirectory = new SimpleObjectProperty<>();

    @FXML
    TableView tableView;

    @FXML
    Label labelNumberOfExtracted;

    @FXML
    Button buttonExport;

    @FXML
    Label labelPath;

    @FXML
    Button buttonOpen;

    @FXML
    RadioButton radioButtonMandatory;

    @FXML
    RadioButton radioButtonAll;

    protected String couldNotExtractMessage = "Could not extract metadata...";

    protected String numberOfExtractedText = "Extracted metadata: ";

    protected Map<String,Object> currentExtractedMetadata = null;

    public UserDatasetController(){

    }

    private ObservableList<String> availableMetas, selectedMetas;

    private List<String> defaultAvailableMetas;

    private Main main;

    public void init(){
        List<String> meta = new MiscellaneousDao().selectMetadataPossibleToExtract();
        allMetadata = meta;
        List<String> selectedMandatory = new MiscellaneousDao().selectMandatoryMetadata();

        //remove selected
        meta = meta.stream().filter(s -> !selectedMandatory.contains(s)).collect(Collectors.toList());

        defaultAvailableMetas = new ArrayList<>(meta);
        availableMetas = FXCollections.observableList(meta);

        availableMetadataListView.setItems(new SortedList<>(availableMetas, String::compareTo));

        selectedMetas = FXCollections.observableList(selectedMandatory);

        selectedMetadataListView.setItems(new SortedList<>(selectedMetas, String::compareTo));


        labelSelectedDirectory.textProperty().bind(Bindings.createStringBinding(
                () -> selectedDirectory.get() != null ? selectedDirectory.get().getAbsolutePath() : "<no directory selected>", selectedDirectory));
        labelPath.textProperty().bind(Bindings.createStringBinding(
                () -> testFile.get() != null ? testFile.get().getAbsolutePath() : "<no file selected>", testFile));
    }

    @Override
    public void buttonClearClicked(ActionEvent args) {
       availableMetas.addAll(selectedMetas);
        selectedMetas.clear();

    }

    @Override
    public void buttonResetClicked(ActionEvent args) {
        selectedMetas.clear();
        selectedMetas.addAll(new MiscellaneousDao().selectMandatoryMetadata());
        availableMetas.clear();
        availableMetas.addAll(defaultAvailableMetas);
    }

    @Override
    protected void addMetadata(String selected) {
        if(!selectedMetadataListView.getItems().contains(selected)){
            selectedMetas.add(selected);
            availableMetas.remove(selected);
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

    protected void removeMetadata(String selected) {
        selectedMetas.remove(selected);
        availableMetas.add(selected);
        selectedMetadataListView.getSelectionModel().clearSelection();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    protected TabPane mainTabs;
    public TabPane getMainTabs() {
        return mainTabs;
    }

    public void setMainTabs(TabPane mainTabs) {
        this.mainTabs = mainTabs;
    }

    public void buttonExtractClicked(ActionEvent args){
        UIUtils ui = new UIUtils();
        if(labelPath.getText() == null || labelPath.getText().isEmpty() || labelPath.getText().contains("<not selected>")){
            ui.showWarning("Select dataset first!");
            return;
        }

        if(this.selectedMetadataListView.getItems().size() == 0){
            ui.showWarning(this.noMetadataWarning);
            return;
        }

        File file = new File(labelPath.getText());
        MetadataExtractor extractor = new MetadataExtractor();
        Map<String,Object> metadata = null;

        if(radioButtonMandatory.isSelected()) {
            try {
                metadata = extractor.extractMetadata(file,this.selectedMetadataListView.getItems());
            } catch (Exception e) {
                e.printStackTrace();
                ui.showWarning(couldNotExtractMessage);
            }
        }
        else {
            try {
                metadata = extractor.extractAllMetadata(file);
            } catch (Exception e) {
                e.printStackTrace();
                ui.showWarning(couldNotExtractMessage);
            }
        }


        if(metadata == null){
            ui.showWarning(couldNotExtractMessage);
            return;
        }
        List<String> columns = getMetadataAttributeNames(metadata);
        ui.putDataIntoTableView(mapAsList(metadata),new MetadataDao(),columns,this.tableView);
        labelNumberOfExtracted.setText(numberOfExtractedText + columns.size());
        currentExtractedMetadata = metadata;
    }

    public void buttonOpenClicked(ActionEvent args){
        FileUtils fileUtils = new FileUtils();
        File file = fileUtils.openFileViaDialog(fileUtils.getArffExtensions(),main.getPrimaryStage());
        if(file != null && file.exists() && !file.isDirectory()){
            testFile.set(file);
        }
    }

    protected List<Map<String, Object>> mapAsList(Map<String, Object> metadata) {
        List<Map<String,Object>> forTable = new ArrayList<>();
        forTable.add(metadata);
        return forTable;
    }

    protected List<String> getMetadataAttributeNames(Map<String, Object> metadata) {
        List<String> columns = new ArrayList<>();
        for (Map.Entry<String, Object> entry :
                metadata.entrySet()) {
            columns.add(entry.getKey());
        }
        return columns;
    }

    public File getTempExportfile(){
        if(currentExtractedMetadata == null){
            new UIUtils().showWarning("No metadata is generated");
            return null;
        }

        try{
            FileUtils fileUtils = new FileUtils();
            File file = File.createTempFile("temp_test_file", ".csv");  //fileUtils.saveFileViaDialog(fileUtils.getCsvExtensions(), main.getPrimaryStage());
            List<String> columns = getMetadataAttributeNames(currentExtractedMetadata);
            fileUtils.saveToCsv(file, columns, mapAsList(currentExtractedMetadata));
            return file;
        }
        catch(Exception ex){
          ex.printStackTrace();
        }
        return null;
    }


    public void recommend(ActionEvent actionEvent) {
        File selectedDirectoryFile = selectedDirectory.get();
        File testFile = getTempExportfile();
        if(selectedDirectoryFile != null && selectedDirectoryFile.exists() && selectedDirectoryFile.isDirectory() && selectedDirectoryFile.listFiles() != null &&
                testFile != null && testFile.exists() && !testFile.isDirectory()){
            final List<File> collect = Arrays.stream(selectedDirectoryFile.listFiles())
                    .filter(fileInPath -> !fileInPath.isDirectory() && fileInPath.getName().toLowerCase().trim().endsWith(".csv"))
                    .collect(Collectors.toList());

            final String classifierName = "weka.classifiers.trees.M5P";
            final String filterName = "weka.filters.unsupervised.instance.Randomize";

            Map<File, Double> fileToCoef = new HashMap<>(collect.size());

            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call()
                                throws InterruptedException {
                            try {
                                int i = 0;
                                updateMessage("Running weka...");
                                for (File file : collect) {
                                    updateProgress(i++, collect.size());
                                    WekaRunner wekaRunner = new WekaRunner(classifierName, new String[]{},
                                            filterName, new String[]{}, file.getAbsolutePath(), testFile.getAbsolutePath());

                                    wekaRunner.execute();
                                    System.out.println("FILE: " + file);
                                    System.out.println(wekaRunner.toString());
                                    System.out.println("END OF FILE: " + file);
                                    double v = wekaRunner.getEvaluation().meanAbsoluteError();
                                    fileToCoef.put(file, v);
                                }
                                updateProgress(i, collect.size());
                                updateMessage("Done.");
                            } catch (Exception e) {
                                e.printStackTrace();
                                final String msg;
                                if(e.getCause() != null){
                                    msg = e.getCause().getMessage();
                                } else {
                                    msg = e.getMessage();
                                }
                                final String header;
                                if(e.getCause().getMessage().contains("differ in # of attributes")){
                                    header = "Wrong number of chosen attributes.";
                                } else {
                                    header = "Exception occurs";
                                }
                                Platform.runLater(() -> showWarning(header, msg));
                            } finally {
                                Map.Entry<File, Double> min = fileToCoef.entrySet().stream().min((o1, o2) -> o1.getValue().compareTo(o2.getValue())).get();
                                File recommendedFile = min.getKey();
                                Platform.runLater(() -> {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Done message");
                                    alert.setContentText("Recommended algorithm: " + recommendedFile.getName());
                                    alert.show();
                                });
                            }
                            return null;
                        }
                    };
                }
            };

            ProgressDialog progressDialog = new ProgressDialog(service);
            progressDialog.setTitle("Work in progress.");
            progressDialog.setHeaderText("Recommending...");
            service.start();
        }
    }

    private static String getAlgorithmName(File csvFile){
        try(FileReader fr = new FileReader(csvFile); BufferedReader bufferedReader = new BufferedReader(fr)){
            bufferedReader.readLine();  // header
            String secondLine = bufferedReader.readLine();
            String algorithName = secondLine.split(",", 4)[2];
            return algorithName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void buttonOpenPathClicked(ActionEvent actionEvent) {
        DirectoryChooser fileChooser = new DirectoryChooser ();
        fileChooser.setInitialDirectory(FileUtils.LAST_FILE);
        File file = fileChooser.showDialog(main.getPrimaryStage());
        if(file != null) {
            FileUtils.LAST_FILE = file.getParentFile();
        }
        if(file != null && file.exists() && file.isDirectory()){
            selectedDirectory.set(file);
        }
    }
}
