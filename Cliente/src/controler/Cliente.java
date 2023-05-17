package controler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Gabriel
 */
public class Cliente extends Application {
    
    public Cliente(){
    
    
    }
   @Override
   /**
 * Método start
 * Carga la ventana de inicio de sesión, establece la etapa (stage) y muestra la ventana.
 * También envía un mensaje al servidor.
 */
    public void start(Stage pstage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Cliente.class.getResource("/visual/login_FXML.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            pstage.setScene(scene);
            C_Login_FXML c = loader.getController();
            c.setStage(pstage);
            pstage.show();
            this.send("hola server");
        }catch(IOException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setTitle("Error");
            a.setContentText("Error cargando la ventana");
            a.showAndWait();  
            
            System.err.println(e);
        }
    }

    /**
     * @param args the command line arguments
     * 
     *  Método main
     * Punto de entrada para la ejecución de la aplicación.
     */

     public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Método send
     * Envía un mensaje al servidor y espera la respuesta.
     * Utiliza un socket para establecer la conexión con el servidor y enviar y recibir datos.
     */
    public static String send(String a){
        final String HOST = "127.0.0.1";
        final int PORT = 5000;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            Socket clientSocket = new Socket(HOST, PORT);

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF(a);

            String message = in.readUTF();
            System.out.println("I recieve: "+message+"1");
            

            clientSocket.close();
            return message;
        } catch (IOException e) {
            System.out.println(e+"1");
        }
        return null;
        }
    
    
}
