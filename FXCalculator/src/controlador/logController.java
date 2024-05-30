package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class logController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextArea textArea1;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ArrayList<String> llistaOp = new ArrayList<String>();
        ArrayList<String> llistaFecha = new ArrayList<String>();
        String[] guardarInfo;
        Scanner sc;
        String formatarOp = "";
        try {
            sc = new Scanner(new File("src/fichero/log.txt"));

            while (sc.hasNext()) {
                guardarInfo = sc.nextLine().split("-->");
                llistaOp.add(guardarInfo[0]);
                llistaFecha.add(guardarInfo[1]);
            }

            StringBuilder sbOp = new StringBuilder();
            StringBuilder sbFecha = new StringBuilder();
            
            for (int i = 0; i < llistaOp.size(); i++) {
            	formatarOp = String.format("%s",llistaOp.get(i));
                sbOp.append(formatarOp).append("\n");
                sbFecha.append(llistaFecha.get(i)).append("\n");
            }

            textArea.setText(sbOp.toString());
            
            textArea1.setText(sbFecha.toString());
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void salir(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }

}
