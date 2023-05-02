package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class C_Login_FXML implements Initializable {
    private Stage stg;
    @FXML
    private Button btnLogIn;
    @FXML
    private PasswordField pwPassword;
    @FXML
    private TextField tfUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void load_c() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/client_FXML.fxml"));
        
        Parent root = loader.load();
        
        C_Client_FXML controlador = loader.getController();
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
         
        controlador.set_stage(stage);
        
        stage.setScene(scene);
        
        stage.show();
        
        stage.setOnCloseRequest(e -> controlador.closeTab());
        
        this.stg.close();
    }
    
    private void load_a() throws IOException {
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/visual/admin_FXML.fxml"));

        Parent root = loader.load();

        C_Admin_FXML controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        controlador.set_stage(stage);

        stage.setScene(scene);

        stage.show();

        stage.setOnCloseRequest(e -> controlador.closeTab());

        this.stg.close();
        
    }
    
    public void set_stage(Stage e){this.stg = e;}

    @FXML
    private void logIn(ActionEvent event) throws Exception {
        String msg=Cliente.send("Buscar "+this.tfUser.getText()+" "+this.pwPassword.getText());
        if(msg.equals("Admin")){
            load_a();
        }else if(msg.equals("Cliente")){
            load_c();
        }else{
            System.out.println("Usuario o Contraseña invalida");
        }
    }
    
    public void setStage(Stage stage){
        this.stg=stage;
    }
}
