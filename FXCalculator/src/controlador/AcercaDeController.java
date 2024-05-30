package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AcercaDeController implements Initializable{

    @FXML
    private Button btnCerrar;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	btnCerrar.setOnAction(event -> cerrar(event));
    }

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }
}
