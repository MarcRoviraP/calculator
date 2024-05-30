package controlador;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import aplicacion.Main;
import excepciones.DivisionPorCeroException;
import excepciones.RaizNegativaException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import modelo.Calculadora;
import utilidades.I18N;

public class CalculadoraController implements Initializable {

	private Calculadora calculadora;

	@FXML
	private Button btn0;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn5;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn8;

	@FXML
	private Button btn9;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnC;

	@FXML
	private Button btnCE;

	@FXML
	private Button btnComa;

	@FXML
	private Button btnDiv;

	@FXML
	private Button btnIgual;

	@FXML
	private Button btnInvertir;

	@FXML
	private Button btnMC;

	@FXML
	private Button btnMMenos;

	@FXML
	private Button btnMR;

	@FXML
	private Button btnMS;

	@FXML
	private Button btnMas;

	@FXML
	private Button btnMenos;

	@FXML
	private Button btnMmas;

	@FXML
	private Button btnMult;

	@FXML
	private Button btnPorcentaje;

	@FXML
	private Button btnRaiz;

	@FXML
	private Button btnSigno;

	@FXML
	private MenuItem español;

	@FXML
	private MenuItem ingles;

	@FXML
	private Label lblMemoria;

	@FXML
	private Label lblOperacions;

	@FXML
	private Menu menuAyuda;

	@FXML
	private Menu menuEdicio;

	@FXML
	private Menu menuIdioma;

	@FXML
	private Menu menuVer;

	@FXML
	private MenuItem menu_AcercaDe;

	@FXML
	private MenuItem menu_Ayuda;

	@FXML
	private MenuItem menu_Copiar;

	@FXML
	private MenuItem menu_Historial;

	@FXML
	private MenuItem menu_Pegar;

	@FXML
	private MenuItem menu_Salir;

	@FXML
	private AnchorPane panelHead;

	@FXML
	private Pane panelMain;

	@FXML
	private Label txtArea;

	@FXML
	private Text txtFecha;

	@FXML
	private Text txtNom;

	@FXML
	private MenuItem valenciano;
	@FXML
	private MenuItem japones;

	@FXML
	private Button modoClaro;

	@FXML
	private Button modoOscuro;

	// Teclas

	void setIdioma(String idioma) {
		I18N.setLocale(new Locale(idioma));

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		// Stage stage = (Stage) panelHead.getScene().getWindow();
		//
		// stage.initStyle(StageStyle.TRANSPARENT);
		menu_Historial.textProperty().bind(I18N.createStringBinding("form.historial"));
		menu_Salir.textProperty().bind(I18N.createStringBinding("form.salir"));

		menu_Copiar.textProperty().bind(I18N.createStringBinding("form.copiar"));
		menu_Pegar.textProperty().bind(I18N.createStringBinding("form.pegar"));
		//
		menu_Ayuda.textProperty().bind(I18N.createStringBinding("form.ayuda"));
		menu_AcercaDe.textProperty().bind(I18N.createStringBinding("form.acerca"));
		//
		menuIdioma.textProperty().bind(I18N.createStringBinding("form.idioma"));
		menuVer.textProperty().bind(I18N.createStringBinding("form.ver"));
		menuAyuda.textProperty().bind(I18N.createStringBinding("form.ayuda"));
		menuEdicio.textProperty().bind(I18N.createStringBinding("form.edicio"));

		// Accions del menu

		español.setOnAction((event) -> setIdioma("es"));
		valenciano.setOnAction((event) -> setIdioma("ca"));
		ingles.setOnAction((event) -> setIdioma("en"));
		japones.setOnAction((event) -> setIdioma("ja"));

		// Botons idiomes

		valenciano.textProperty().bind(I18N.createStringBinding("form.ca"));
		español.textProperty().bind(I18N.createStringBinding("form.es"));
		ingles.textProperty().bind(I18N.createStringBinding("form.en"));
		japones.textProperty().bind(I18N.createStringBinding("form.ja"));

		// Crear la Calculadora
		calculadora = new Calculadora();
		// Asignar los Eventos de las opciones de menu TODO
		menu_Salir.setOnAction((event) -> salir());
		// Asignar los Eventos a los botones

		// Botones Operaciones
		btnMas.setOnMouseClicked((event) -> asignarOperacion("+"));
		btnMenos.setOnMouseClicked((event) -> asignarOperacion("-"));
		btnDiv.setOnMouseClicked((event) -> asignarOperacion("/"));
		btnMult.setOnMouseClicked((event) -> asignarOperacion("*"));
		btnPorcentaje.setOnMouseClicked((event) -> porcentaje());

		// Igual
		btnIgual.setOnMouseClicked((event) -> calcular());
		// Raiz
		btnRaiz.setOnMouseClicked((event) -> raiz());

		// Botones numeros
		btn1.setOnMouseClicked((event) -> insertarNumero("1"));
		btn2.setOnMouseClicked((event) -> insertarNumero("2"));
		btn3.setOnMouseClicked((event) -> insertarNumero("3"));
		btn4.setOnMouseClicked((event) -> insertarNumero("4"));
		btn5.setOnMouseClicked((event) -> insertarNumero("5"));
		btn6.setOnMouseClicked((event) -> insertarNumero("6"));
		btn7.setOnMouseClicked((event) -> insertarNumero("7"));
		btn8.setOnMouseClicked((event) -> insertarNumero("8"));
		btn9.setOnMouseClicked((event) -> insertarNumero("9"));
		btn0.setOnMouseClicked((event) -> insertarNumero("0"));

		// Invertir
		btnInvertir.setOnMouseClicked((event -> {
			try {
				inversa();
			} catch (DivisionPorCeroException e) {

				errorDiv();
			}
		}));

		// Cambiar signo
		btnSigno.setOnMouseClicked((event) -> cambiarSigno());

		// Retroceder

		btnBack.setOnMouseClicked((event) -> retroceder());

		// Botones de memoria

		btnMC.setOnMouseClicked((event) -> memoryClear());
		btnMR.setOnMouseClicked((event) -> memoryRecall());
		btnMS.setOnMouseClicked((event) -> memoryStorage());
		btnMmas.setOnMouseClicked((event) -> memorySumar());
		btnMMenos.setOnMouseClicked((event) -> memoryRestar());

		// Botones clear
		btnC.setOnMouseClicked((event) -> clear());
		btnCE.setOnMouseClicked((event) -> clearError());

		// Coma
		btnComa.setOnMouseClicked((event) -> insertarComa());

		modoClaro.setOnMouseClicked((event) -> {
			cambiarTema("claro");
			modoClaro.setDisable(true);
			modoClaro.setVisible(false);
			modoOscuro.setDisable(false);
			modoOscuro.setVisible(true);
		});
		modoOscuro.setOnMouseClicked((event) -> {
			cambiarTema("oscuro");
			modoClaro.setDisable(false);
			modoClaro.setVisible(true);
			modoOscuro.setDisable(true);
			modoOscuro.setVisible(false);
		});

		// Asignar los Eventos de las opciones de menu
		menu_AcercaDe.setOnAction((event) -> mostrarVentana("/vista/AcercaDe.fxml", menu_AcercaDe.getText()));
		menu_Ayuda.setOnAction((event) -> mostrarVentana("/vista/Ayuda.fxml", menu_Ayuda.getText()));
		menu_Historial.setOnAction((event) -> mostrarVentana("/vista/log.fxml", menu_Historial.getText()));

		// Insertar fuente externa

		InputStream fontStream = getClass().getResourceAsStream("/utilidades/Calculator.ttf");

		@SuppressWarnings("unused")
		Font calculator = Font.loadFont(fontStream, 12);

		// Crear un Timeline para actualizar el texto del txtFecha cada segundo

		actualizarHora();
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> actualizarHora()));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	private void actualizarHora() {

		// Formata la hora
		LocalDateTime ahora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(I18N.createStringBinding("form.fecha").get());
		String horaFormateada = ahora.format(formatter);
		txtFecha.setText(horaFormateada);
	}

	// Mostrar otra ventana
	private void mostrarVentana(String rutaFXML, String titulo) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
			Parent root = (Parent) fxmlLoader.load();

			Stage stage = new Stage();

			stage.setTitle(titulo);
			stage.setResizable(false);
			stage.setScene(new Scene(root));

			// Spawnear sense bordes
			stage.initStyle(StageStyle.TRANSPARENT);
			// Hacer modal la ventana
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();

		} catch (Exception e) {

		}
	}

	@FXML
	void pulsarTecla(KeyEvent event) {
		switch (event.getCode()) {
		case DIGIT0:
		case NUMPAD0:
			insertarNumero("0");
			break;
		case DIGIT1:
		case NUMPAD1:
			insertarNumero("1");
			break;
		case DIGIT2:
		case NUMPAD2:
			insertarNumero("2");
			break;
		case DIGIT3:
		case NUMPAD3:
			insertarNumero("3");
			break;
		case DIGIT4:
		case NUMPAD4:
			insertarNumero("4");
			break;
		case DIGIT5:
		case NUMPAD5:
			insertarNumero("5");
			break;
		case DIGIT6:
		case NUMPAD6:
			insertarNumero("6");
			break;
		case DIGIT7:
		case NUMPAD7:
			insertarNumero("7");
			break;
		case DIGIT8:
		case NUMPAD8:
			insertarNumero("8");
			break;
		case DIGIT9:
		case NUMPAD9:
			insertarNumero("9");
			break;

		case PLUS:
		case ADD:
			asignarOperacion("+");
			break;
		case MINUS:
		case SUBTRACT:
			asignarOperacion("-");
			break;
		case MULTIPLY:
			asignarOperacion("*");
			break;
		case DIVIDE:
			asignarOperacion("/");
			break;
		case ENTER:
		case FINAL:
			calcular();
			break;

		case COMMA:
		case DECIMAL:
			insertarComa();
			break;

		case BACK_SPACE:
			retroceder();
			break;

		case DELETE:
			clearError();
			break;

		case ESCAPE:
			clear();
			break;
		default:
			break;
		}

		KeyCombination multiply = new KeyCodeCombination(KeyCode.PLUS, KeyCodeCombination.SHIFT_DOWN);
		if (multiply.match(event)) {
			asignarOperacion("*");
		}
		KeyCombination div = new KeyCodeCombination(KeyCode.DIGIT7, KeyCodeCombination.SHIFT_DOWN);
		if (div.match(event)) {
			asignarOperacion("/");
		}
		KeyCombination percentaje = new KeyCodeCombination(KeyCode.DIGIT5, KeyCodeCombination.SHIFT_DOWN);
		if (percentaje.match(event)) {
			porcentaje();
		}
		KeyCombination igual = new KeyCodeCombination(KeyCode.DIGIT0, KeyCodeCombination.SHIFT_DOWN);
		if (igual.match(event)) {
			calcular();
		}
		KeyCombination mc = new KeyCodeCombination(KeyCode.L, KeyCodeCombination.CONTROL_DOWN);
		if (mc.match(event)) {
			memoryClear();
		}
		KeyCombination mr = new KeyCodeCombination(KeyCode.R, KeyCodeCombination.CONTROL_DOWN);
		if (mr.match(event)) {
			memoryRecall();
		}
		KeyCombination ms = new KeyCodeCombination(KeyCode.M, KeyCodeCombination.CONTROL_DOWN);
		if (ms.match(event)) {
			memoryStorage();
		}
		KeyCombination mMenos = new KeyCodeCombination(KeyCode.P, KeyCodeCombination.CONTROL_DOWN);
		if (mMenos.match(event)) {
			memoryRestar();
		}
		KeyCombination mMas = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);
		if (mMas.match(event)) {
			memorySumar();
		}
	}

	public void porcentaje() {

		try {
			calculadora.porcentaje();
		} catch (DivisionPorCeroException e) {
			calculadora.clear();
			errorDiv();
		}
		mostrarInfo(calculadora.getNumActualReal());
	}

	public void raiz() {

		try {
			calculadora.raiz();
			mostrarInfo(calculadora.getNumActualReal());
		} catch (RaizNegativaException e) {

			calculadora.clear();
			errorRaiz();

		}

	}

	private void cambiarTema(String ruta) {

		String nuevaRuta = "/vista/" + ruta + ".css";
		Scene scene = panelHead.getScene();

		scene.getStylesheets().clear();
		scene.getStylesheets().add(getClass().getResource(nuevaRuta).toExternalForm());

	}

	private void insertarComa() {

		calculadora.insertarComa();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void insertarNumero(String numero) {

		calculadora.insertarNumero(numero);
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void asignarOperacion(String operacion) {
		try {
			calculadora.asignarOperacion(operacion);
			if (operacion.equals("%")) {

				mostrarInfo(calculadora.getNumActual() + "");
			} else {

				mostrarInfo(calculadora.getNum1() + "");
			}

		} catch (DivisionPorCeroException e) {

			clear();
			errorDiv();
		}

	}

	private void retroceder() {

		calculadora.retroceder();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void cambiarSigno() {

		calculadora.cambiarSigno();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void calcular() {

		try {
			calculadora.calcular(false);
			mostrarInfo(calculadora.getNum1() + "");

		} catch (DivisionPorCeroException e) {

			clear();

			errorDiv();
		}

	}

	private void inversa() throws DivisionPorCeroException {
		calculadora.inversa();
		mostrarInfo(calculadora.getNum1() + "");
	}

	private void clearError() {

		calculadora.clearError();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void clear() {
		calculadora = new Calculadora();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void memoryClear() {
		calculadora.memoryClear();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void memoryStorage() {
		calculadora.memoryStorage();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void memoryRecall() {
		calculadora.memoryRecall();
		mostrarInfo(calculadora.getNumMemoria());
	}

	private void mostrarInfo(String info) {

		txtArea.setText(calculadora.formatarNumero(info));
		lblOperacions.setText(calculadora.imiprimirCalcul());
		lblMemoria.setText(calculadora.formatarNumero(calculadora.getNumMemoria()));

	}

	private void memorySumar() {
		calculadora.memorySumar();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void memoryRestar() {
		calculadora.memoryRestar();
		mostrarInfo(calculadora.getNumActualReal());
	}

	private void errorDiv() {

		txtArea.setText(I18N.createStringBinding("form.errorDiv").get());
	}

	private void errorRaiz() {
		txtArea.setText(I18N.createStringBinding("form.errorRaiz").get());
	}

	private void salir() {
		Stage stage = (Stage) menu_Salir.getParentPopup().getOwnerWindow();
		stage.close();

	}
}
