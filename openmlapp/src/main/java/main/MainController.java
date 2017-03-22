package main;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private WekaController wekaController;
    @FXML
    private AlgorithmByClassController algorithmByClassController;

    @FXML
    private ResultsController resultsController;

    @FXML
    private AlgorithmByVersionController algorithmByVersionController;

    @FXML private AlgorithmByParametersController algorithmByParametersController;


    @FXML private UserDatasetController userDatasetController;

    @FXML public TabPane tabPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wekaController.init();
        algorithmByClassController.init();
        algorithmByVersionController.init();
        algorithmByParametersController.init();
        resultsController.init();
        userDatasetController.init();
    }

    public void setMain(Main main){
        wekaController.setMain(main);

        algorithmByClassController.setMainTabs(tabPane);
        algorithmByClassController.setMain(main);

        algorithmByVersionController.setMainTabs(tabPane);
        algorithmByVersionController.setMain(main);

        algorithmByParametersController.setMainTabs(tabPane);
        algorithmByParametersController.setMain(main);

        resultsController.setMainTabs(tabPane);
        resultsController.setMain(main);

        userDatasetController.setMainTabs(tabPane);
        userDatasetController.setMain(main);
    }


}
