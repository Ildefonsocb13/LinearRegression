//
//  Lectura.java
//  
//
//  Created by Castro Bouquet Ildefonso on 09/03/2021.
//  Created by Cevallos Vega Bernardo Ernesto on 09/03/2021
//  Created by Angeles Garcia Daniela Fernanda on 09/03/2021
//  Created by Escamilla Garcia Derek Fernando on 09/03/2021
//
package com.lectura;

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//class Lectura
public class Lectura {
    // Variable declaration
    private ArrayList<String> listaDatos = new ArrayList<String>();
    private String[] arregloTemporal;
    private String[][] separarFilas;
    private String path;
    private int longitud;
    private double[] datos;

    // Constructor
    Lectura(String path) {
        this.path = path;
    }

    /*
     * This method reads all file and saves it in an arrayList
     * 
     * @returns (Arraylist) listaDatos: info from file.
     */
    public ArrayList<String> readFile() throws Exception {
        try {
            Scanner fp = new Scanner(new File(path));
            while (fp.hasNext()) {// lee linea por linea del archivo
                listaDatos.add(fp.nextLine());
            }
            fp.close();
        } catch (FileNotFoundException ex) {
            ex.toString();
            System.out.println("No se encontro el archivo.");
            System.exit(0);
        }

        return listaDatos;
    }

    /*
     * This method saves on a double array values from a specific column from an
     * ArrayList
     * 
     * @Param (Arraylist) listaDatos: info from file. (Int) columna: which column to
     * convert.
     * 
     * @returns (double[]) datos: values of column from Arraylist converted to
     * double.
     */
    public double[] separarFilas(ArrayList<String> tabla, int columna) {
        arregloTemporal = tabla.toArray(new String[1]);
        separarFilas = new String[arregloTemporal.length][];

        for (int i = 0; i < arregloTemporal.length; i++) {
            separarFilas[i] = arregloTemporal[i].split(",");
        }

        longitud = separarFilas.length;
        datos = new double[longitud - 1];

        for (int i = 1; i < longitud; i++) {
            datos[i - 1] = Double.parseDouble(separarFilas[i][columna]);
        }
        return datos;
    }
}
