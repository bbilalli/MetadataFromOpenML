package util;

import dao.MetadataDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marcin on 6/4/16.
 */
public class UIUtils {
    public void putDataIntoTableView(List<Map<String, Object>> results, MetadataDao metadataDao, List<String> columnsToAdd, TableView tableView) {
        tableView.getColumns().clear();
        List<TableColumn> tableColumns = new ArrayList<>();
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

        tableView.getColumns().addAll(tableColumns);
        tableView.setItems(FXCollections.observableList(results));
    }

    public void showWarning(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(text);
        alert.setContentText("");

        alert.showAndWait();
    }
}
