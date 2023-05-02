package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class C_Client_FXML implements Initializable {
    private Stage stg;
    @FXML
    private Label lbl;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void closeTab(){
        try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/login_FXML.fxml"));

               Parent root = loader.load();

               C_Login_FXML controlador = loader.getController();

               Scene scene = new Scene(root);
               Stage stage = new Stage();
  
               controlador.set_stage(stage);
               
               stage.setScene(scene);

               stage.show();

               this.stg.close();
        }catch(IOException e){
        
        }
    }
    
    public void set_stage(Stage e){this.stg = e;}
}
