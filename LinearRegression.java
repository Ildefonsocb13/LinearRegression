//
//  LinearRegression.java
//  
//
//  Created by Castro Bouquet Ildefonso on 23/02/2021.
//
package com.lectura;

//class LinearRegression
public class LinearRegression {
    // variables
    private double[] A = { 0, 0 };
    private double[] B = { 0, 0 };

    /*
     * Constructor
     */
    public LinearRegression(double[] A, double[] B) {
        this.A = A;
        this.B = B;
    }

    /*
     * This method calculates average in array
     * 
     * @returns (double) suma/A.length: array average.
     */
    public double averageX() {
        int i;
        double sum = 0;
        for (i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum / A.length;
    }

    /*
     * This method calculates average in array
     * 
     * @returns (double) sum/B.length: array average.
     */
    public double averageY() {
        int i;
        double sum = 0;
        for (i = 0; i < B.length; i++) {
            sum += B[i];
        }
        return sum / B.length;
    }

    /*
     * This method calculates b0
     * 
     * @returns (double) result: answer.
     */
    public double b0() {
        double result;
        result = averageY() - (b1() * averageX());
        return result;
    }

    /*
     * This method calculates b1
     * 
     * @returns (double) result: answer.
     */
    public double b1() {
        double result = 0;
        try {
            result = (covarianza() / varianza());
        } catch (Exception e) {
            System.out.println("\"A\" and \"B\" length are not equal.");
        }
        return result;
    }

    /*
     * This method calculates covarianza
     * 
     * @returns (double) sum: covarianza.
     */
    public double covarianza() throws Exception {
        if (A.length != B.length) {
            throw new ArithmeticException("\"A\" and \"B\" Length is not the same.");
        } else {
            double sum = 0;
            int i;
            for (i = 0; i < A.length; i++) {
                sum += (A[i] - averageX()) * (B[i] - averageY());
            }
            return sum;
        }
    }

    /*
     * This method calculates varianza
     * 
     * @returns (double) sum: varianza.
     */
    public double varianza() {
        int i;
        double sum = 0;
        double asd = 0;// xi -averagex
        for (i = 0; i < A.length; i++) {
            asd = A[i] - averageX();
            sum += (Math.pow(asd, 2));
        }
        return sum;
    }

    /*
     * This method calculates value for Error
     * 
     * @returns (double) sum: Error percentage.
     */
    public double errorValue() {
        int i;
        double sum = 0;
        for (i = 0; i < B.length; i++) {
            sum += Math.pow((B[i] - averageY()), 2);
        }
        sum = Math.sqrt(sum / (B.length - 1));
        return sum;
    }
}
