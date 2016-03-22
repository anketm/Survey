/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author anket
 */
public class Survey extends Application {
    final static Logger logger = Logger.getLogger(Survey.class);
    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Start Called");
        Parent root = FXMLLoader.load(getClass().getResource("SurveyForm.fxml"));
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();
        logger.info("Start End");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
