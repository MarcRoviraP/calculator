package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import excepciones.*;

public class Calculadora {

	private BigDecimal num1;
	private BigDecimal num2;

	private String numActual;
	private String numActualTMP;
	private String numMemoria;
	private static String[] simbolos = { "+", "-", "/", "*", "%" };
	private String operacio;
	ArrayList<String> guardarCalculo;
	ArrayList<String> guardarCalculoTMP;

	public Calculadora() {

		clear();
		guardarCalculo = new ArrayList<String>();
		guardarCalculoTMP = new ArrayList<String>();

	}

	// TODO fer operacions
	public BigDecimal sumar() {
		return this.num1.add(num2);
	}

	public BigDecimal restar() {

		return this.num1.subtract(num2);
	}

	public BigDecimal multiplicar() {

		return this.num1.multiply(num2);
	}

	public BigDecimal dividir() throws DivisionPorCeroException {

		return dividirGeneral(this.num1, this.num2);
	}

	public BigDecimal dividirGeneral(BigDecimal n1, BigDecimal n2) throws DivisionPorCeroException {

		BigDecimal b;
		String numReal;

		try {
			if (n2.equals(BigDecimal.ZERO)) {
				throw new DivisionPorCeroException();
			}

			b = n1.divide(n2);
			numReal = b + "";

			if (numReal.contains("E")) {

				b = n1.divide(n2, RoundingMode.UP);
			}

			return b;
		} catch (ArithmeticException e) {
			return n1.divide(n2, 16, RoundingMode.HALF_UP);
		}
	}

	// Operaciones aritméticas
	public void porcentaje() throws DivisionPorCeroException {

		try {

			this.numActual = dividirGeneral(new BigDecimal(this.numActual), new BigDecimal(100)) + "";
			if (this.operacio.equals("+") || this.operacio.equals("-")) {

				this.numActual = this.num1.multiply(new BigDecimal(this.numActual)) + "";

			}
			calcular(true);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

	}

	public void raiz() throws RaizNegativaException {

		BigDecimal raiz = new BigDecimal(numActualTMP);

		// La raíz es menor que cero
		if (raiz.compareTo(BigDecimal.ZERO) < 0) {

			throw new RaizNegativaException();
		} else {

			this.num1 = raiz.sqrt(new MathContext(20));
			this.numActual = num1 + "";
			igualarANumActual();
		}
	}

	// Cambiar signo al valor actual
	public void cambiarSigno() {

		if (this.numActual.equals("")) {
			igualarANumActualTMP();
		}
		if (!this.numActual.isEmpty()) {

			if (this.numActual.charAt(0) == '-') {

				this.numActual = this.numActual.substring(1);
				igualarANumActual();
			} else {

				if (!this.numActual.equals("0")) {

					this.numActual = "-" + numActual;
				}
				igualarANumActual();
			}
		}
	}

	// Resetear todas las variables
	public void clear() {

		this.num1 = new BigDecimal(0);
		this.num2 = new BigDecimal(0);
		this.numActual = "";
		this.numActualTMP = "0";
		this.numMemoria = "0";
		this.operacio = "";
		guardarCalculo = new ArrayList<String>();

	}

	// Concatenar número al numActual
	public String concatenar(String numero) {

		return "";
	}

	public void retroceder() {

		if (this.numActual.length() > 0) {

			this.numActual = this.numActual.substring(0, this.numActual.length() - 1);

			if (this.numActual.isEmpty() || this.numActual.equals("-")) {
				this.numActual = "0";
			}
			igualarANumActual();

		}
	}

	public void memoryClear() {

		this.numMemoria = "0";
	}

	public void memoryStorage() {

		this.numMemoria = this.numActual;
	}

	public void memoryRecall() {

		if (!this.numMemoria.equals("")) {

			this.numActual = this.numMemoria;
		}

	}

	public void memorySumar() {

		this.numMemoria = new BigDecimal(numMemoria).add(new BigDecimal(numActualTMP)) + "";
	}

	public void memoryRestar() {

		this.numMemoria = new BigDecimal(numMemoria).subtract(new BigDecimal(numActualTMP)) + "";

	}

	public BigDecimal getNum1() {
		return this.num1;
	}

	public void setNum1(BigDecimal num1) {
		this.num1 = num1;
	}

	public BigDecimal getNum2() {
		return num2;
	}

	public void setNum2(BigDecimal num2) {
		this.num2 = num2;
	}

	public String getOperacion() {
		return operacio;
	}

	public String getNumActualReal() {
		return numActual;
	}

	public String getNumActual() {
		return numActualTMP;
	}

	public String getNumMemoria() {
		return numMemoria;
	}

	public void setNumMemoria(String numMemoria) {
		this.numMemoria = numMemoria;
	}

	public void asignarOperacion(String operacio) throws DivisionPorCeroException {

		String simbolo = "";
		try {

			if (this.numActual == "" && operacio.equals("-")) {
				// Asigna el menor deavant del numero
				this.numActual = "-";
			} else {
				try {

					if (!numActual.equals("")) {

						guardarCalculo.add(numActual);
						guardarCalculoTMP.add(numActual);
					}
					simbolo = guardarCalculo.get(guardarCalculo.size() - 1);

					for (String s : simbolos) {

						if (s.equals(simbolo)) {
							guardarCalculo.remove(guardarCalculo.size() - 1);
							guardarCalculoTMP.remove(guardarCalculoTMP.size() - 1);
							this.numActual = this.num1 + "";
							this.num1 = new BigDecimal(0);
							this.num2 = new BigDecimal(0);
							break;
						}
					}
				} catch (IndexOutOfBoundsException e) {
				}
				if (!operacio.equals("")) {
					calcular(true);
				}
				this.operacio = operacio;

				if (this.operacio.equals("√")) {

					guardarCalculo.add(this.operacio);
					guardarCalculoTMP.add(this.operacio);
				} else {

					if (this.numActual == "" && this.operacio.equals("-")) {
						// Asigna el menor deavant del numero
					} else {
						this.num1 = new BigDecimal(numActual);
						if (!this.numActual.equals("") && guardarCalculo.size() == 0) {

							guardarCalculo.add(this.numActual);
							guardarCalculoTMP.add(this.numActual);
						}
						if (!numActual.equals("")) {

							guardarCalculo.add(operacio);
							guardarCalculoTMP.add(operacio);
							numActual = "";
						}
					}

				}

			}
		} catch (NumberFormatException e) {
			clear();
		}

	}

	public void inversa() throws DivisionPorCeroException {

		try {

			if (numActual.equals("")) {

				igualarANumActualTMP();
			}
			this.numActual = dividirGeneral(new BigDecimal(1), new BigDecimal(numActual)) + "";
			igualarANumActual();
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
	}

	public void calcular(boolean valor) throws DivisionPorCeroException {
		try {

			if (numActual.equals("")) {
				igualarANumActualTMP();
			}
			this.num2 = new BigDecimal(numActual);
			BigDecimal res = new BigDecimal(0);
			switch (this.operacio) {
			case "+":
				res = sumar();
				break;
			case "-":
				res = restar();
				break;
			case "*":
				res = multiplicar();
				break;
			case "/":
				res = dividir();
				break;

			default:

				break;
			}

			// res.scale >= 1 busca que tenga algun deciamñ
			// res.stripTrailingZeros elimina los ceros inecesarios
			if (res.scale() >= 1) {
				res = res.stripTrailingZeros();
			}

			if (this.operacio.equals("")) {

				this.numActual = this.num2 + "";
				this.num1 = this.num2;

			} else {

				this.numActual = res + "";
				this.num1 = res;
			}

			guardarCalculo.clear();
			guardarCalculo.add(this.numActual);

			igualarANumActual();

			if (!valor) {

				// Guardar log

				LocalDateTime ahora = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
				String horaFormateada = ahora.format(formatter);

				guardarCalculoTMP.add(this.num2 + " = " + this.numActual);

				File f = new File("src/fichero/log.txt");
				try {
					PrintWriter pw = new PrintWriter(new FileWriter(f, true));
					pw.println(String.format("%s-->%s",imiprimirCalculTMP(),horaFormateada));

					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("No existe el fichero");
				}

				guardarCalculoTMP.clear();
				guardarCalculoTMP.add(numActual);

				this.numActual = "";

			}

			this.operacio = "";
		} catch (NumberFormatException e) {

			this.numActual = "0";
		}
	}

	public void insertarNumero(String numActual) {

		if (numActual.equals(".")) {
			insertarComa();
		} else {

			BigDecimal n = new BigDecimal(numActual);
			if (n.equals(BigDecimal.ZERO)) {

				this.numActual += numActual;
				igualarANumActual();
			} else {

				n = new BigDecimal(this.numActual + numActual);
				this.numActual = n + "";
				igualarANumActual();
			}
		}

	}

	public void clearError() {

		this.numActual = "";

	}

	public String imiprimirCalcul() {

		String calc = "";

		for (String calcul : guardarCalculo) {

			if (esOperacio(calcul, simbolos)) {

				calc += calcul + " ";
			} else {

				calc += formatarNumero(calcul) + " ";
			}
		}
		return calc;
	}

	public String imiprimirCalculTMP() {

		String calc = "";

		for (String calcul : guardarCalculoTMP) {

			if (esOperacio(calcul, simbolos)) {

				calc += calcul + " ";
			} else {

				calc += formatarNumero(calcul) + " ";
			}
		}
		return calc;
	}

	public boolean esOperacio(String op, String[] opcions) {

		boolean valor = false;

		for (String opcion : opcions) {

			if (op.equals(opcion) || op.isEmpty()) {

				valor = true;
				break;
			}
		}
		return valor;
	}

	public void insertarComa() {

		if (this.numActual.equals("")) {

			this.numActual += "0";
		}
		if (!this.numActual.equals("") && !this.numActual.contains(".")) {

			this.numActual += ".";
		}
	}

	public String formatarNumero(String num) {

		try {

			BigDecimal b;
			if (num.equals("")) {
				num = "0";
			}
			DecimalFormatSymbols simbol = new DecimalFormatSymbols();

			simbol.setDecimalSeparator(',');
			simbol.setGroupingSeparator('.');
			DecimalFormat formato = new DecimalFormat("#,###.#########", simbol);
			b = new BigDecimal(num);
			num = formato.format(b);
		} catch (NumberFormatException e) {
		}
		return num;
	}

	public void igualarANumActual() {

		this.numActualTMP = numActual;
	}

	public void igualarANumActualTMP() {

		this.numActual = numActualTMP;
	}
}
