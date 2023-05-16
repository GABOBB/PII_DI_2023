package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    
    private ObservableList<Platillo> pedir_platillos_anadidos;
    
    private ComboBox<Platillo> CB_platillos;
    
     @FXML
    private ComboBox<Platillo> pedir_CB_platillos = new ComboBox<>();
    @FXML
    private TableView<Platillo> pedir_Lista_de_pedidos_act_tv;
    @FXML
    private TableColumn pedir_c_platillos_tc;
    @FXML
    private TextField pedir_Nomnbre_pedido_tf;
    @FXML
    private TextField pedir_calorias_pedido_tf;
    @FXML
    private TextField pedir_precio_pedido_tf;
    @FXML
    private TextField pedir_tiempo_pedido_tf;
    @FXML
    private Button pedir_anadir_a_pedido_bt;
    @FXML
    private Button pedir_eliminar_platillo_bt;
    @FXML
    private Button pedir_mandar_pedido_bt;
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
        this.pedir_platillos_anadidos = FXCollections.observableArrayList();
        this.pedir_c_platillos_tc.setCellValueFactory(new PropertyValueFactory("id"));
        
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
        try{
        this.pedir_CB_platillos.getItems().clear();
        String PLATILLOS = Cliente.send("get_platillos");
            System.out.println(PLATILLOS);
                if(PLATILLOS != null){
                    String Platillos = PLATILLOS.substring(3);
                    String platillos[] = Platillos.split("###");

                    for(String sp : platillos){
                        System.out.println(sp);
                        String auxp[] = sp.split(";");
                        String id = auxp[0];
                        int c = Integer.parseInt(auxp[1]);
                        int t = Integer.parseInt(auxp[2]);
                        int p = Integer.parseInt(auxp[3]);
                        Platillo P = new Platillo(id,c,t,p); 
                        this.pedir_CB_platillos.getItems().add(P);
                    }

                }
        }catch(Exception e){
            //e.printStackTrace();
        }
    }
    
    @FXML
    private void pedir_seleccionar_platillo(ActionEvent event) {
        Platillo p = this.pedir_CB_platillos.getValue();
        if(p!=null){
            this.pedir_Nomnbre_pedido_tf.setText(p.getId());
            this.pedir_precio_pedido_tf.setText("$"+p.getPrecio());
            this.pedir_calorias_pedido_tf.setText(p.getCalorias()+"cal");
            this.pedir_tiempo_pedido_tf.setText(p.getTiempo()+"s");
        }
    }

    @FXML
    private void pedir_anadir_platillo(ActionEvent event) {
        Platillo p = this.pedir_CB_platillos.getValue();
        this.pedir_platillos_anadidos.add(p);
        this.pedir_Lista_de_pedidos_act_tv.setItems(pedir_platillos_anadidos);
    }


    @FXML
    private void pedir_eliminar_platillo(ActionEvent event) {
        Platillo p = this.pedir_Lista_de_pedidos_act_tv.getSelectionModel().getSelectedItem();
        if(this.pedir_platillos_anadidos.contains(p)){
            this.pedir_platillos_anadidos.remove(p);
            this.pedir_Lista_de_pedidos_act_tv.refresh();
            this.pedir_Nomnbre_pedido_tf.setText(null);
            this.pedir_calorias_pedido_tf.setText(null);
            this.pedir_precio_pedido_tf.setText(null);
            this.pedir_tiempo_pedido_tf.setText(null);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("");
            alert.showAndWait();
        
        }
    }

    @FXML
    private void pedir_mandar_pedido(ActionEvent event) {
        String total = "nuevo_pedido";
        for(Platillo p : this.pedir_platillos_anadidos){
            String temp = "###";
            temp+=p.getId()+";";
            temp+=p.getCalorias()+";";
            temp+=p.getTiempo()+";";
            temp+=p.getPrecio();
        }
        Cliente.send(total);
    }

//##############################################Pedidos Activos#################################################


//##############################################Historial#######################################################

    



}
