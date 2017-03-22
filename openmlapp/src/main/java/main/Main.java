package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;


    protected List<Map<String,Object>> currentResults = null;

    protected List<String> currentMetaAttributes = null;

    static final String LAST_PATH_KEY = "last_path";

    @Override
    public void init() throws Exception {
        super.init();
        File propFile = new File("preferences.properties");
        if(propFile.exists()) {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream(propFile));
                String value = properties.getProperty(LAST_PATH_KEY);
                File file = new File(value);
                if(file.exists())
                    FileUtils.LAST_FILE = file;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        File propFile = new File("preferences.properties");
        propFile.delete();
        Properties properties = new Properties();
        properties.put(LAST_PATH_KEY, FileUtils.LAST_FILE.getAbsolutePath());
        try {
            properties.store(new FileOutputStream(propFile), "no comment...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("OpenML App");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


        MainController mainController = loader.getController();
        mainController.setMain(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }

    public List<Map<String, Object>> getCurrentResults() {
        return currentResults;
    }

    public void setCurrentResults(List<Map<String, Object>> currentResults) {
        this.currentResults = currentResults;
    }

    public List<String> getCurrentMetaAttributes() {
        return currentMetaAttributes;
    }

    public void setCurrentMetaAttributes(List<String> currentMetaAttributes) {
        this.currentMetaAttributes = currentMetaAttributes;
    }
}
