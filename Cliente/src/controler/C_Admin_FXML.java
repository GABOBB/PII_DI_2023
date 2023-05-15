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
    
    @FXML
    private Button new_admin_bt;
    @FXML
    private Button delet_admin_bt;
    @FXML
    private Button modificar_admin_bt;
    
//############################################Cola de Pedidos############################################################
    @FXML
    private Button C_P_B;
    @FXML
    private AnchorPane C_P_A_P;
    
    private ObservableList<Platillo> pedidos_en_cola;
    @FXML
    private TableView<Platillo> cola_tv;
    @FXML
    private TableColumn cola_cv;
    @FXML
    private Text time_label;
//############################################Menu#######################################################################
    @FXML
    private Button M_B;
    @FXML
    private AnchorPane m_A_P;
    
    private ObservableList<Platillo> Menu_platillos_ol;
    @FXML
    private TableView<Platillo> Menu_tv;
    @FXML
    private TableColumn Menu_platillo_tc;
    @FXML
    private TextField Menu_id_tf;
    @FXML
    private TextField Menu_Precio_tf;
    @FXML
    private TextField Menu_Calorias_tf;
    @FXML
    private TextField Menu_TIempo_tf;
    @FXML
    private Button Menu_add_platillo_B;
    @FXML
    private Button Menu_delete_platillo_B;
    @FXML
    private Button Menu_Modificar_platillo_B;
//############################################LOG OUT####################################################################
    @FXML
    private Button Log_OUT;

    
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
        this.admins = FXCollections.observableArrayList();
        this.LD_c.setCellValueFactory(new PropertyValueFactory("user"));
    //######################cola de pedidos############################################################
        this.pedidos_en_cola = FXCollections.observableArrayList();
        this.cola_cv.setCellValueFactory(new PropertyValueFactory("id"));
    //######################platillos en el menu#####################################################################
        this.Menu_platillos_ol = FXCollections.observableArrayList();
        this.Menu_platillo_tc.setCellValueFactory(new PropertyValueFactory("id"));
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
        
            load_Menu();
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
        this.pedidos_en_cola.clear();
        String returned = Cliente.send("get_pedidos");
        if(returned != null){
            String pedidos[] = returned.split("###");
            if(pedidos != null){
                for(int x = 1; x < pedidos.length; x++){
                    String pedido  = pedidos[x];
                    String[] i = pedido.split(";");
                    if(i.length == 5){
                        Platillo p = new Platillo(  i[0],
                                                    i[1],
                                                    Integer.parseInt(i[2]),
                                                    Integer.parseInt(i[3]),
                                                    Integer.parseInt(i[4]));
                        this.pedidos_en_cola.add(p);
                    }
                    this.cola_tv.setItems(this.pedidos_en_cola);
                }

            }
        }
    }



//##########################################metodos del menu############################################################################   
    private void load_Menu(){
        String PLATILLOS = Cliente.send("get_platillos");
        if(PLATILLOS != null){
            String aux[] = PLATILLOS.split("###");
            String platillos[] = new String[aux.length-1];
            System.arraycopy(aux, 1, platillos, 0, platillos.length);
            for(String platillo : platillos){
                String p[] = platillo.split(";");
                Platillo N_P = new Platillo(p[0],
                                            Integer.parseInt(p[1]),
                                            Integer.parseInt(p[2]),
                                            Integer.parseInt(p[3]));
                this.Menu_platillos_ol.add(N_P);
            }
            this.Menu_tv.setItems(this.Menu_platillos_ol);
        }
    }
    
    @FXML
    private void Menu_add_platillo(ActionEvent event) {
        String id = this.Menu_id_tf.getText();
        String cal = this.Menu_Calorias_tf.getText();
        String tmp = this.Menu_TIempo_tf.getText();
        String prc = this.Menu_Precio_tf.getText();
        if(id == null || cal == null || tmp == null || prc == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("deve de rellenar todos los datos para poder anadir un platillos nuevo");
            alert.showAndWait();
            return;
        }
        try{
            Platillo p = new Platillo(id,
                                    Integer.parseInt(cal),
                                    Integer.parseInt(tmp),
                                    Integer.parseInt(prc));
            if(!this.Menu_platillos_ol.contains(p)){
                this.Menu_platillos_ol.add(p);
                this.Menu_tv.setItems(this.Menu_platillos_ol);
                Menu_to_string();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error en los datos");
                alert.setContentText("Este Platillo ya existe en el menu");
                alert.showAndWait();
            
            }
            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("Existe un error en los datos introducidos");
            alert.showAndWait();
        
        }
    }

    @FXML
    private void Menu_delete_platillo(ActionEvent event) {
        Platillo P = this.Menu_tv.getSelectionModel().getSelectedItem();
        if(P == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("deve de seleccionar para eliminarlo");
            alert.showAndWait();
        
        }else{
            this.Menu_platillos_ol.remove(P);
            this.Menu_tv.refresh();
            this.Menu_id_tf.setText(null);
            this.Menu_Calorias_tf.setText(null);
            this.Menu_TIempo_tf.setText(null);
            this.Menu_Precio_tf.setText(null);
            Menu_to_string();
        
        }
    }

    @FXML
    private void Menu_Modificar_platillo(ActionEvent event) {
        Platillo p = this.Menu_tv.getSelectionModel().getSelectedItem();
        if(p == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("deve de seleccionar un platillo para modificarlo");
            alert.showAndWait();
        }else{
            String id = this.Menu_id_tf.getText();
            int calorias = Integer.parseInt(this.Menu_Calorias_tf.getText());
            int tiempo = Integer.parseInt(this.Menu_TIempo_tf.getText());
            int precio = Integer.parseInt(this.Menu_Precio_tf.getText());
            
            Platillo aux = new Platillo(id,calorias,tiempo,precio);
            
            if(!this.Menu_platillos_ol.contains(aux)){
                p.setId(id);
                p.setCalorias(calorias);
                p.setTiempo(tiempo);
                p.setPrecio(precio);
                
                this.Menu_tv.refresh();
                Menu_to_string();
            
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Este platillo ya existe en el menu");
                alert.showAndWait();
            
            } 
            
        }        
    }

    @FXML
    private void menu_selecionar_platillo(MouseEvent event) {
        
        Platillo p = this.Menu_tv.getSelectionModel().getSelectedItem();
        this.Menu_id_tf.setText(p.getId());
        this.Menu_Calorias_tf.setText(p.getCalorias()+"");
        this.Menu_TIempo_tf.setText(p.getTiempo()+"");
        this.Menu_Precio_tf.setText(p.getPrecio()+"");
    }
    
    private void Menu_to_string(){
        String total = "actualizar platillos";
        for(Platillo p : this.Menu_platillos_ol){
            
            String temp = "###";
            temp += p.getId() + ";";
            temp += p.getCalorias() + ";";
            temp += p.getTiempo() + ";";
            temp += p.getPrecio();
            
            total += temp;
        }
        Cliente.send(total);
    }
    
//#########################################metodos de cerrar ventana####################################################################
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
