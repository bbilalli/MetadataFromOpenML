package main;

import dao.MetadataDao;
import entities.FullMetaData;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by marcin on 26.05.16.
 */
public class ResultsController {
    public static String CSV_DELIMITER = ",";

    @FXML
    public TableView tableView;

    protected TabPane mainTabs;
    public TabPane getMainTabs() {
        return mainTabs;
    }

    public void setMainTabs(TabPane mainTabs) {
        this.mainTabs = mainTabs;

        if(mainTabs == null){return;}

        bindToTabChange();

    }

    public void buttonRefreshClicked(ActionEvent args) {
        refreshResults();
    }

    protected void bindToTabChange() {
        this.mainTabs.getSelectionModel().selectedItemProperty().addListener((args, oldTab, newTab)->{
            if(newTab.getId() != null && newTab.getId().equals("resultTab")){
                refreshResults();
            }
        });
    }

    private void refreshResults() {
        if(this.main != null && this.main.getCurrentResults() != null && this.main.getCurrentMetaAttributes() != null && this.tableView != null){
            List<Map<String,Object>> results = this.main.getCurrentResults();

            //set columns
            this.tableView.getColumns().clear();
            List<TableColumn> tableColumns = new ArrayList<>();
            MetadataDao metadataDao = new MetadataDao();
            List<String> columnsToAdd = getAllAtrributes(metadataDao);
            for (String columnName : columnsToAdd) {

                //items of column
                if(columnName.equals(metadataDao.scoreField)){
                    TableColumn<Map<String,Object>, Double> tableColumn = new TableColumn<>(columnName);
                    tableColumn.setCellValueFactory(param -> new SimpleObjectProperty<>((Double) param.getValue().get(metadataDao.scoreField)));
                    tableColumns.add(tableColumn);
                }
                else {
                    TableColumn<Map<String,Object>, Object> tableColumn = new TableColumn<>(columnName);
                    tableColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().get(columnName)));
                    tableColumns.add(tableColumn);
                }
            }

            this.tableView.getColumns().addAll(tableColumns);
            this.tableView.setItems(FXCollections.observableList(results));

        }
    }

    private List<String> getAllAtrributes(MetadataDao metadataDao) {
        List<String> columnsToAdd = new ArrayList<>();
        columnsToAdd.addAll(metadataDao.basicAttributesList);
        columnsToAdd.addAll(this.main.getCurrentMetaAttributes());
        return columnsToAdd;
    }

    public void init(){

    }

    private Main main;
    public void setMain(Main main) {
        this.main = main;
    }


    public void buttonExportClicked(ActionEvent args){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();

        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter("CSV file", "*.csv");
        fileChooser.getExtensionFilters().addAll(csv);

        fileChooser.setTitle("Save metadata");
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

        List<String> allAttributes = getAllAtrributes(new MetadataDao());
        List<Map<String,Object>> results = this.main.getCurrentResults();

        writeToCsv(file, allAttributes, results);
    }

    public static void writeToCsv(File file, List<String> allAttributesInit, List<Map<String,Object>> results) {
        Locale aDefault = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);
        List<String> allAttributes = new ArrayList<>(allAttributesInit);
        allAttributes.remove("did");
        allAttributes.remove("measure");
        allAttributes.remove("algorithm_class");
        allAttributes.remove("algorithm");
        allAttributes.remove("algorithm_parameters");
        allAttributes.remove("name");
        try(PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8")) {
            writer.println(String.join(CSV_DELIMITER,allAttributes));
            for(Map<String,Object> result : results){
                List<String> values = allAttributes.stream().map(attr->{
                    Object x =  result.get(attr);
                    if(x != null){
                        if(x instanceof Double) {
                            return String.format("%.15f", (Double)x);
                        }

                        String retStr = x.toString();
                        retStr = retStr.replaceAll(",", ";");

                        if(retStr.matches("^\\-?[0-9]+\\.[0-9]+E[\\-|\\+]?[0-9]+$")){
                            double var;
                            try {
                                var = Double.parseDouble(retStr.replace("+", "").replace("E", "e"));
                            } catch (NumberFormatException ex) {
                                var = 0.0d;
                                ex.printStackTrace();
                            }
                            return String.format("%.15f", var);
                        }
                        return retStr;
                    }
                    return "";
                }).collect(Collectors.toList());
                writer.println(String.join(CSV_DELIMITER,values));
            }

            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Locale.setDefault(aDefault);
        }
    }

}
