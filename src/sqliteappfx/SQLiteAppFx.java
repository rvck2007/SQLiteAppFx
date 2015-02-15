/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteappfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Herve
 */
public class SQLiteAppFx extends Application {

    private int year;
    
    @Override
    public void start(Stage stage) throws Exception {
        
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
         // Parent root = FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
        
        Scene scene = new Scene(root);
        
        String css = "sqliteappfx/Style.css";
        
        scene.getStylesheets().add(css);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
