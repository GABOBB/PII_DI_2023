package controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class C_Login_FXML implements Initializable {
    private Stage stg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void load_c() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/client_FXML.fxml"));
        
        Parent root = loader.load();
        
        C_Admin_FXML controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
         
        
        stage.setScene(scene);
        
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeTab());
        
        this.stg.close();
    }
    
    private void load_a() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/admin_FXML.fxml"));
        
        Parent root = loader.load();
        
        C_Admin_FXML controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeTab());
        
        this.stg.close();
    }
    
    public void set_stage(Stage e){this.stg = e;}
    
}
