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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Admin;
import model.Platillo;

/**
 * FXML Controller class
 *
 * @author Gabriel
 */
public class C_Admin_FXML implements Initializable {
    private Stage stg;
    
//############################################main#######################################################################
    @FXML
    private Button main_B;
    @FXML
    private AnchorPane main_A_P;  
//######################administracion de usuarios############################################################    
    @FXML
    private Button L_U_B;
    @FXML
    private AnchorPane L_U_A_P;
    @FXML
    private TableView<Admin> LD_t;//tabla de los administradores
    @FXML
    private TableColumn LD_c;//colubna de administradores
    
    private ObservableList<Admin> admins;
    
    @FXML
    private TextField LD_u_tf;
    @FXML
    private TextField LD_c_tf;
    
//############################################Cola de Pedidos############################################################
    @FXML
    private Button C_P_B;
    @FXML
    private AnchorPane C_P_A_P;
    
    private ObservableList<Platillo> pedidos_en_cola;
//############################################Menu#######################################################################
    @FXML
    private Button M_B;
    @FXML
    private AnchorPane m_A_P;
//############################################LOG OUT####################################################################
    @FXML
    private Button Log_OUT;
    @FXML
    private Button new_admin_bt;
    @FXML
    private Button delet_admin_bt;
    @FXML
    private Button modificar_admin_bt;
    @FXML
    private TableView<?> cola_tv;
    @FXML
    private TableColumn<?, ?> cola_cv;
    @FXML
    private Text time_label;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.main_A_P.setVisible(true);
        this.C_P_A_P.setVisible(false);
        this.L_U_A_P.setVisible(false);
        this.m_A_P.setVisible(false);
        
        
    //######################administracion de usuarios############################################################
        admins = FXCollections.observableArrayList();
        this.LD_c.setCellValueFactory(new PropertyValueFactory("user"));
    //######################administracion de usuarios############################################################
    }    


    
   

    @FXML
    private void A_P_selector(ActionEvent e) {
        if(main_B==e.getSource()){
            
            this.main_A_P.setVisible(true);
            this.C_P_A_P.setVisible(false);
            this.L_U_A_P.setVisible(false);
            this.m_A_P.setVisible(false);
            
        }else if(L_U_B==e.getSource()){
            
            this.load_L_U(Cliente.send("lista de administradores").split("###"));
            this.main_A_P.setVisible(false);
            this.C_P_A_P.setVisible(false);
            this.L_U_A_P.setVisible(true);
            this.m_A_P.setVisible(false);
            
            
            
            
        }else if(C_P_B==e.getSource()){
            
            this.load_pedidos();
            this.main_A_P.setVisible(false);
            this.C_P_A_P.setVisible(true);
            this.L_U_A_P.setVisible(false);
            this.m_A_P.setVisible(false);   
        
        }else if(M_B == e.getSource()){
        
            this.main_A_P.setVisible(false);
            this.C_P_A_P.setVisible(false);
            this.L_U_A_P.setVisible(false);
            this.m_A_P.setVisible(true);
            
        }
    }
    
    
//############################################metodos del main menu####################################################################





//###########################################metodos de lista de usuarios##############################################################
    private void load_L_U(String as[]){
        this.LD_u_tf.setText(null);
        this.LD_c_tf.setText(null);
        this.admins.clear();
        this.LD_t.setItems(admins);
        for(int i=1; i<as.length; i++){
            String t = as[i];
            Admin a = new Admin(t.split(";")[0],t.split(";")[1]);
            admins.add(a);
        }
        this.LD_t.setItems(admins);
    }
    
    private void show_admin(String u, String p){
        Admin new_A = new Admin(u,p);
        
        if(!this.admins.contains(new_A)){
            this.admins.add(new_A);
            this.LD_t.setItems(admins);
            this.admins_to_string();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("estas credenciales ya estan en uso, por favor intente otras");
            alert.showAndWait(); 
        
        }
    }
    @FXML
    private void new_user_admin(ActionEvent event) {
        String u = this.LD_u_tf.getText();
        String p = this.LD_c_tf.getText();
        if((u==null || p == null) || (u.isBlank() || p.isBlank())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setTitle("Error");
            a.setContentText("deve de llenar ambos espacio");
            a.showAndWait();  
        }else{
            
            show_admin(u,p);
        }
    }

        @FXML
    private void selecionar_admin(MouseEvent event) {
        Admin a = this.LD_t.getSelectionModel().getSelectedItem();
        if(a!=null){
            this.LD_u_tf.setText(a.getUser());
            this.LD_c_tf.setText(a.getPssw());
        }
    }

    @FXML
    private void eliminar_admin(ActionEvent event) {
        Admin a = this.LD_t.getSelectionModel().getSelectedItem();
        if(a!=null){
            this.admins.remove(a);
            this.LD_t.refresh();
            this.LD_u_tf.setText(null);
            this.LD_c_tf.setText(null);
            this.admins_to_string();
            
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("deve de seleccionar un usuario administrador para eliminarlo");
            alert.showAndWait(); 
        }
    }

    @FXML
    private void modificar_admin(ActionEvent event) {
        Admin a = this.LD_t.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("deve de seleccionar un usuario administrador para modificarlo");
            alert.showAndWait();  
        }else{
            
            String u = this.LD_u_tf.getText();
            String p = this.LD_c_tf.getText();
            
            Admin ad_ax = new Admin(u,p);
            
            if(!this.admins.contains(ad_ax)){
                a.setUser(u);
                a.setPssw(p);
                
                this.LD_t.refresh();
                admins_to_string();
                
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("estas credenciales ya estan en uso, por favor intente otras");
                alert.showAndWait();  
            }

            
            
            
        
        }
    }

    private void admins_to_string(){
        String total = "Actualizar_Administradores";
        int num =  this.admins.size();
        for(int i = 0;i<num; i++){
            Admin temp = this.admins.get(i);
            String u = temp.getUser();
            String p = temp.getPssw();
            
            String tempT = "###" + u + ";" + p;
            total += tempT;
        }
        System.out.println(total);
        this.load_L_U(Cliente.send(total).split("###"));
    }
//###########################################metodos de cola de pedidos#################################################################

    private void load_pedidos(){
        String returned = Cliente.send("get_pedidos");
        if(returned != null){
            String pedidos[] = returned.split("###");
            if(pedidos != null){
                for(String pedido : pedidos){

                    String[] i = pedido.split(";");
                    if(i.length == 5){
                        Platillo p = new Platillo(  i[0],
                                                    i[1],
                                                    Integer.parseInt(i[2]),
                                                    Integer.parseInt(i[3]),
                                                    Integer.parseInt(i[4]));

                    }
                }

            }
        }
    }



//##########################################metodos del menu############################################################################   
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



}
