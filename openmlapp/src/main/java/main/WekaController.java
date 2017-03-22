package main;


import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import util.WekaRunner;
import weka.core.Attribute;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class WekaController {

    public static final String DELIM = ",";

    @FXML Button buildModelButton;
    @FXML CheckListView<String> checkListView;
    @FXML TextArea logTextArea;
    @FXML TextField classifier, classifierOptions, filter, filterOptions;

    private SimpleObjectProperty<Instances> instances = new SimpleObjectProperty<>();

    private BooleanProperty runningTask = new SimpleBooleanProperty(false);

    private Main main;

    public void init() {
        instances.addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                ArrayList<Attribute> attributes = Collections.<Attribute>list(newValue.enumerateAttributes());
                checkListView.getItems().setAll(attributes.stream().map(Attribute::name).collect(Collectors.toList()));
                logTextArea.appendText(newValue.toString() + "\n");
            } else {
                checkListView.getItems().clear();
                logTextArea.appendText("\nInstances is null!\n");
            }
        });

        logTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            logTextArea.setScrollTop(Double.MAX_VALUE);
        });

        BooleanBinding cannotBuildBinding = Bindings.size(checkListView.getCheckModel().getCheckedItems()).isEqualTo(0)
                .or(classifier.textProperty().isEmpty())
                .or(runningTask)
                .or(filter.textProperty().isEmpty());

        buildModelButton.disableProperty().bind(cannotBuildBinding);

        initClassifiers();
        initFilters();
        initDefaults();
    }

    private void initDefaults() {
        classifier.setText("weka.classifiers.bayes.NaiveBayes");
        filter.setText("weka.filters.unsupervised.instance.Randomize");
    }
    private void initFilters() {
        try {
            ClassPath classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClassesRecursive("weka.filters");

            AutoCompletionBinding<String> acb = TextFields.bindAutoCompletion(filter ,
                    topLevelClasses.stream().map(ClassPath.ClassInfo::getName).collect(Collectors.toList()));
            acb.setVisibleRowCount(4);
            acb.setHideOnEscape(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initClassifiers() {
        try {
            ClassPath classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
            ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClassesRecursive("weka.classifiers");

            AutoCompletionBinding<String> acb = TextFields.bindAutoCompletion(classifier ,
                    topLevelClasses.stream().map(ClassPath.ClassInfo::getName).collect(Collectors.toList()));
            acb.setVisibleRowCount(4);
            acb.setHideOnEscape(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().clear();

        FileChooser.ExtensionFilter arff = new FileChooser.ExtensionFilter("ARFF file", "*.arff");
        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter("CSV file", "*.csv");
        fileChooser.getExtensionFilters().addAll(arff, csv);

        fileChooser.setTitle("Choose arf file");
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if(file != null){
            loadWekaFile(file);
        }
    }

    private void loadWekaFile(File file) {
        logTextArea.appendText("Loading file: " + file.getAbsolutePath() + ".\n");
        this.instances.set(WekaRunner.loadTrainingInstanceSecured(file));
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void buildModel(ActionEvent actionEvent) {
        runningTask.setValue(true);

        final String classifierName = classifier.getText();
        final String[] classifierOptions = this.classifierOptions.getText().split(DELIM);

        final String filterName = filter.getText();
        final String[] filterOptions = this.filterOptions.getText().split(DELIM);

        Thread wekaRunnerThread = new Thread(() -> {
            try {
                Instances instances = WekaRunner.selectAttributes(this.instances.get(), checkListView.getCheckModel().getCheckedItems());
                WekaRunner wekaRunner = new WekaRunner(classifierName, classifierOptions, filterName, filterOptions, instances);
                wekaRunner.execute();
                logTextArea.appendText(wekaRunner.toString() + "\n");
            } finally {
                Platform.runLater(() -> runningTask.set(false));
            }
        });
        wekaRunnerThread.setDaemon(true);
        wekaRunnerThread.start();
    }

    public void selectAllAction(ActionEvent actionEvent) {
        checkListView.getCheckModel().checkAll();
    }
}
