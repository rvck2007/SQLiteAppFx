/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteappfx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.Statement;

/**
 *
 * @author Herve
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Label invalid_label;
    
    @FXML
    private TextField username_box;
    
    @FXML
    private TextField password_box;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        System.out.println("You clicked me!");
        
        // définir la page parente
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
        
        Scene home_page_scene = new Scene(home_page_parent);
        
        String css = "sqliteappfx/Style.css";
        
        home_page_scene.getStylesheets().add(css);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
            if (isValidCredentials())
            {   
                /* Si les éléments la méthode isValidCredentials retourne true,
                    ce qui veut dire que les éléments saisis dans les champs Username et Password sont corrects,
                    alors la fenêtre courante (fenêtre de connexion) est masquée...
                */
                app_stage.hide();   // optional

                /**
                 * et on accède à la fenêtre d'accueil de l'application est chargée...
                 */
                app_stage.setScene(home_page_scene);
                
                // ...et affichée à l'écran...
                app_stage.show();
            }
            else
            {   
                /**
                 * sinon, si les identifiants saisis sont incorrects...
                 * alors les éléments saisis dans les champs sont effacés
                 */
                username_box.clear();
                password_box.clear();
                /* et un message d'erreur est affiché à l'écran... */
                invalid_label.setText("Désolé, les identifiants saisis ne sont pas valides !");
            }
        
    }
    
    
    private boolean isValidCredentials()
    {
        /**
         * let_in : le boolean retournant true si les identifiants de connexion sont corrects
         *      et false s'ils sont incorrects par rapport aux données de la base SQLite.
         */
        boolean let_in = false;
        
        /* Requête SQL pour vérifier la validité des valeurs saisies dans les champs du formulaire
            avec les données stockées dans la base SQLite :
        */
        System.out.println( "SELECT * FROM users WHERE username = "
                        + "'" + username_box.getText() + "'" + " AND password = "
                        + "'" + password_box.getText() + "'"
        );
        
        Connection connexion = null;
        
        java.sql.Statement statement = null;
        
        try {
            connexion = DriverManager.getConnection("jdbc:sqlite:first.db");
            connexion.setAutoCommit(false);
            
            System.out.println("Connexion à la base de données réussie !");
            statement = connexion.createStatement();
            
            ResultSet resultset = statement.executeQuery("SELECT * FROM users WHERE username = "
                        + "'" + username_box.getText() + "'" + " AND password = "
                        + "'" + password_box.getText() + "'");
            
            while ( resultset.next()) {
                if ( resultset.getString("username") != null && resultset.getString("password") != null ) {
                    String username = resultset.getString("username");
                    System.out.println("USERNAME = " + username );
                    String password = resultset.getString("password");
                    System.out.println("PASSWORD = " + password );
                    let_in = true;

                }
            }
            
            resultset.close();
            statement.close();
            connexion.close();
        
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    
        System.out.println("booléen de test des identifiants : " + let_in);
        if (let_in == true){
            System.out.println("Opération de connexion à l'application réussie avec succès !");
        } else
            System.out.println("Opération de connexion à l'application a échoué ! Ressasissez à nouveau les identifiants ! ");
        
        return let_in;
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
