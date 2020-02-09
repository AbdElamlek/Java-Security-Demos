/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package classloadertest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Abd-Elmalek
 */
public class ClassLoaderTest extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        //..Scene scene = new Scene(root);
        String loader = stage.getClass().getClassLoader().toString();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, loader, null);
        alert.show();
        
       // stage.setScene(scene);
       // stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
