package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utilidades.I18N;

public class AyudaController  implements Initializable{

	@FXML
    private Button btnCerrar;

    @FXML
    private Label limpiarDesc;

    @FXML
    private Label limpiarErrorDesc;

    @FXML
    private Label limpiarErrorTit;

    @FXML
    private Label limpiarTit;

    @FXML
    private Label mMasDesc;

    @FXML
    private Label mMasTit;

    @FXML
    private Label mMenosDesc;

    @FXML
    private Label mMenosTit;

    @FXML
    private Label mcDesc;

    @FXML
    private Label mcTit;

    @FXML
    private Label mrDesc;

    @FXML
    private Label mrTit;

    @FXML
    private Label msDesc;

    @FXML
    private Label msTit;

    @FXML
    private Label titulo;




    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Cerrar la ventana
        stage.close();
    }




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
        titulo.textProperty().bind(I18N.createStringBinding("form.tituloKey"));
        mcTit.textProperty().bind(I18N.createStringBinding("form.mcTit"));
        mcDesc.textProperty().bind(I18N.createStringBinding("form.mcDesc"));
        mrTit.textProperty().bind(I18N.createStringBinding("form.mrTit"));
        mrDesc.textProperty().bind(I18N.createStringBinding("form.mrDesc"));
        msTit.textProperty().bind(I18N.createStringBinding("form.msTit"));
        msDesc.textProperty().bind(I18N.createStringBinding("form.msDesc"));
        mMasTit.textProperty().bind(I18N.createStringBinding("form.mMasTit"));
        mMasDesc.textProperty().bind(I18N.createStringBinding("form.mMasDesc"));
        mMenosTit.textProperty().bind(I18N.createStringBinding("form.mMenosTit"));
        mMenosDesc.textProperty().bind(I18N.createStringBinding("form.mMenosDesc"));
        limpiarErrorTit.textProperty().bind(I18N.createStringBinding("form.clearErrorTit"));
        limpiarErrorDesc.textProperty().bind(I18N.createStringBinding("form.clearErrorDesc"));
        limpiarTit.textProperty().bind(I18N.createStringBinding("form.clearTit"));
        limpiarDesc.textProperty().bind(I18N.createStringBinding("form.clearDesc"));
    }

}
