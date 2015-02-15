/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteappfx;

import java.awt.Desktop;
import java.io.File;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Herve
 */
public class FXMLHomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Desktop desktop = Desktop.getDesktop();
   
   /*
    @FXML
    ComboBox comboBoxSelectYear; */
    
    @FXML
    MenuButton menuButtonSelectYear;
    
    
    @FXML
    private void handleButtonActionOpenFile(ActionEvent event) throws IOException {
   
        System.out.println("You want to open a file !");
            
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        // Filtrer le type de fichier à sélectionner
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        fileChooser.setTitle("DATA VIZOR : Ouvrir le fichier source");

        File defaultDirectory = new File("C:/Users/Herve/Documents/NetBeansProjects/SQLiteAppFx");
        fileChooser.setInitialDirectory(defaultDirectory);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
            
    }
    
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            
        }
    }
    
    
    
    @FXML
    private void menuSelectYear (ActionEvent event) throws IOException {
        
        MenuItem menu = (MenuItem) event.getSource();
        
        menuButtonSelectYear.setText(menu.getText());
        
    }
    
    /*
    @FXML
    private void handleSelectYear(ActionEvent event) throws IOException {
        
        comboBoxSelectYear = new ComboBox();
        
        comboBoxSelectYear.getItems().addAll(
                "2002",
                "2003",
                "2004",
                "2005",
                "2006",
                "2007",
                "2008",
                "2009",
                "2010",
                "2011",
                "2012",
                "2013 (estimations)",
                "2014 (estimations)",
                "2015 (hyposthèses)"    
        );
        
    }
    */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
