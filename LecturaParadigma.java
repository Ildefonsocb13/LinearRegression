//
//  LecturaParadigma.java
//  
//
//  Created by Castro Bouquet Ildefonso on 09/03/2021.
//  Created by Cevallos Vega Bernardo Ernesto on 09/03/2021
//  Created by Angeles Garcia Daniela Fernanda on 09/03/2021
//  Created by Escamilla Garcia Derek Fernando on 09/03/2021
//
package com.lectura;

//imports
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//main class
public class LecturaParadigma {
	public static void main(String[] args) throws Exception {
		// Variable declaration
		double[] avgPob = new double[3];
		double tN15;// tasa natalidad 15-17
		double tN18;// tasa natalidad 18-19
		double tNVio;// tasa natalidad violencia

		/* Open file from indicated Path */
		Scanner input = new Scanner(System.in);
		System.out.println("Introduzca el Path del archivo con los datos: ");
		String nombreArchivo = input.nextLine();

		/* Make a new object */
		Lectura archivo = new Lectura(nombreArchivo);

		// Dynamic array list. java.util.ArrayList
		ArrayList<String> listaDatos = archivo.readFile();

		/* PovPct, brth15to17 */
		double[] tablaX1 = archivo.separarFilas(listaDatos, 1);
		double[] tablaY1 = archivo.separarFilas(listaDatos, 2);

		/* PovPct, brth18-19 */
		double[] tablaX2 = archivo.separarFilas(listaDatos, 1);
		double[] tablaY2 = archivo.separarFilas(listaDatos, 3);

		/* PovPct, teenbrth */
		double[] tablaX3 = archivo.separarFilas(listaDatos, 1);
		double[] tablaY3 = archivo.separarFilas(listaDatos, 4);

		/* For validation purposes */
		/*
		 * System.out.println("valores x: "); for (int i = 0; i < tablaX1.length; i++) {
		 * System.out.println(tablaX1[i]); } System.out.println("valores y: "); for (int
		 * i = 0; i < tablaY1.length; i++) { System.out.println(tablaY1[i]); }
		 * System.out.println("valores x: "); for (int i = 0; i < tablaX2.length; i++) {
		 * System.out.println(tablaX2[i]); } System.out.println("valores y: "); for (int
		 * i = 0; i < tablaY2.length; i++) { System.out.println(tablaY2[i]); }
		 * System.out.println("valores x: "); for (int i = 0; i < tablaX3.length; i++) {
		 * System.out.println(tablaX3[i]); } System.out.println("valores y: "); for (int
		 * i = 0; i < tablaY3.length; i++) { System.out.println(tablaY3[i]); }
		 */

		/* Ask for average values for the 3 options */
		for (int i = 0; i < 3; i++) {
			try {
				System.out.println("Porcentaje pobreza " + (i + 1) + ": ");
				avgPob[i] = input.nextDouble();
			} catch (InputMismatchException error) {
				System.out.println("No es tipo double.");
				avgPob[i] = 0;
			}
		}
		input.close();

		/* Making obejcts for each table of data */
		LinearRegression regresionLineal1 = new LinearRegression(tablaX1, tablaY1);
		LinearRegression regresionLineal2 = new LinearRegression(tablaX2, tablaY2);
		LinearRegression regresionLineal3 = new LinearRegression(tablaX3, tablaY3);

		// regresionLineal1
		System.out.println(
				"El modelo de regresion lineal es : y = " + regresionLineal1.b1() + "x + " + regresionLineal1.b0());
		System.out.println("Con un porcentaje de error de : " + regresionLineal1.errorValue());
		System.out.println();

		// regresionLineal2
		System.out.println(
				"El modelo de regresion lineal es : y = " + regresionLineal2.b1() + "x + " + regresionLineal2.b0());
		System.out.println("Con un porcentaje de error de : " + regresionLineal2.errorValue());
		System.out.println();

		// regresionLineal3
		System.out.println(
				"El modelo de regresion lineal es : y = " + regresionLineal3.b1() + "x + " + regresionLineal3.b0());
		System.out.println("Con un porcentaje de error de : " + regresionLineal3.errorValue());
		System.out.println();

		/* */
		for (int i = 0; i < 3; i++) {
			System.out.println("\nLas tasas para el indice de pobreza de: " + avgPob[i] + " son:");
			tN15 = (regresionLineal1.b1() * avgPob[i]) + regresionLineal1.b0();
			System.out.println("La tasa de natalidad entre 15 y 17 anios es: " + tN15);
			tN18 = (regresionLineal2.b1() * avgPob[i]) + regresionLineal2.b0();
			System.out.println("La tasa de natalidad entre 18 y 19 anios es: " + tN18);
			tNVio = (regresionLineal3.b1() * avgPob[i]) + regresionLineal3.b0();
			System.out.println("La tasa de natalidad asociada con violencia es: " + tNVio);
		}
	}
}
