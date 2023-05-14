package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Platillo;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class C_Client_FXML implements Initializable {
    private Stage stg;
    
//##############################################Main############################################################
    @FXML
    private AnchorPane Main_AP;
    @FXML
    private Button B_main;
//##############################################Realizar Pedidos################################################
    @FXML
    private AnchorPane hacer_pedidos_AP;
    @FXML
    private Button B_realizar_pedidos;
    
    @FXML
    private TableView<Platillo> Lista_de_pedidos_act_tv;
    @FXML
    private TableColumn c_platillos_tc;
    
    private ObservableList<Platillo> platillos;
    
    @FXML
    private ComboBox<Platillo> CB_platillos;
    @FXML
    private TextField Nomnbre_pedido_tf;
    @FXML
    private TextField calorias_pedido_tf;
    @FXML
    private TextField precio_pedido_tf;
    @FXML
    private TextField tiempo_pedido_tf;
//##############################################Pedidos Activos#################################################
    @FXML
    private AnchorPane Pedidos_Activos_AP;
    @FXML
    private Button B_pedidos_activos;
//##############################################Historial#######################################################
    @FXML
    private AnchorPane Historial_Pedidos_AP;
    @FXML
    private Button B_historial;
//##############################################################################################################
    @FXML
    private Button B_log_out;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.Main_AP.setVisible(true);
        this.hacer_pedidos_AP.setVisible(false);
        this.Pedidos_Activos_AP.setVisible(false);
        this.Historial_Pedidos_AP.setVisible(false);
        
        //##########################################hacer pedidos###############################################
        this.CB_platillos.setItems(platillos);
        
    }    
    
    
    
    @FXML
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

    @FXML
    private void select_window(ActionEvent e) {
        if(e.getSource() == this.B_main){
            
            this.Main_AP.setVisible(true);
            this.hacer_pedidos_AP.setVisible(false);
            this.Pedidos_Activos_AP.setVisible(false);
            this.Historial_Pedidos_AP.setVisible(false);
        
        }else if(e.getSource() == this.B_realizar_pedidos){
        
            this.loadPlatillos();
            this.Main_AP.setVisible(false);
            this.hacer_pedidos_AP.setVisible(true);
            this.Pedidos_Activos_AP.setVisible(false);
            this.Historial_Pedidos_AP.setVisible(false);
            
        }else if(e.getSource() == this.B_pedidos_activos){
        
            this.Main_AP.setVisible(false);
            this.hacer_pedidos_AP.setVisible(false);
            this.Pedidos_Activos_AP.setVisible(true);
            this.Historial_Pedidos_AP.setVisible(false);
            
        }else if(e.getSource() == this.B_historial){
        
            this.Main_AP.setVisible(false);
            this.hacer_pedidos_AP.setVisible(false);
            this.Pedidos_Activos_AP.setVisible(false);
            this.Historial_Pedidos_AP.setVisible(true);
            
        }
    }
    
    
//##############################################Main############################################################
    
    
//##############################################Realizar Pedidos################################################
    public void loadPlatillos(){
    String PLATILLOS = Cliente.send("get_platillos");
        if(platillos != null){
            String platillos[] = PLATILLOS.split("###");
            for(String sp : platillos){
                String auxp[] = sp.split(";");
                String id = auxp[0];
                int c = Integer.parseInt(auxp[1]);
                int t = Integer.parseInt(auxp[2]);
                int p = Integer.parseInt(auxp[3]);
                Platillo P = new Platillo(id,c,t,p); 
                this.CB_platillos.getItems().add(P);
            }
            
        }
    }

//##############################################Pedidos Activos#################################################


//##############################################Historial#######################################################
}
