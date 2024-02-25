package ru.ac.uniyar.palochkina;

import javafx.util.Pair;

import java.util.ArrayList;

public class Gauss {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static Pair<Integer, Integer> simplifyPair(Pair<Integer, Integer> pair) {
        int first = 0;
        int second = 1;
        if ( pair.getKey() !=0){
        int gcdValue = gcd(pair.getKey(), pair.getValue());
        first = pair.getKey() / gcdValue;
        second = pair.getValue() / gcdValue;}
        return new Pair<>(first, second);
    }

    public static void simplifyMatrix(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix){
        for (ArrayList<Pair<Integer, Integer>> pairs : matrix) {
            pairs.replaceAll(Gauss::simplifyPair);
        }
    }
    public ArrayList<ArrayList<Pair<Integer, Integer>>>  makeOne(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix) {
        int n = matrix.get(0).size();
        int numRows = matrix.size();
        for (int i = 0; i < numRows; i++) {
            Pair<Integer, Integer> factor = matrix.get(i).get(i);
            matrix.get(i).set(i, new Pair<>(1,1));

            for (int j=i+1; j < n;j++){
                Pair<Integer, Integer> newValue = new Pair<>(matrix.get(i).get(j).getKey()*factor.getValue(),factor.getKey()*matrix.get(i).get(j).getValue());
                matrix.get(i).set(j, newValue);
            }

        }
        return matrix;
    }
    public ArrayList<ArrayList<Pair<Integer, Integer>>> straightMove(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix,  int col) {
        int n = matrix.get(0).size();
        for (int i = 0 ; i < col; i++) {
            for (int j = i+1; j < col; j++) {
                Pair<Integer, Integer> factor = new Pair<>((matrix.get(j).get(i).getKey()*matrix.get(i).get(i).getValue()),matrix.get(i).get(i).getKey()*matrix.get(j).get(i).getValue());
                for (int k = i; k< n ; k++ ) {
                    int numerator = matrix.get(j).get(k).getKey() * factor.getValue() * matrix.get(i).get(k).getValue() - (matrix.get(j).get(k).getValue() * factor.getKey() * matrix.get(i).get(k).getKey());
                    int denominator = matrix.get(j).get(k).getValue()*factor.getValue()*matrix.get(i).get(k).getValue();
                    Pair<Integer, Integer> newValue = new Pair<>(numerator, denominator);
                    matrix.get(j).set(k, newValue);
                }
            }

        }
        simplifyMatrix(matrix);
        return matrix;
    }
 public ArrayList<ArrayList<Pair<Integer, Integer>>> backMove(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix,  int col) {
        matrix = makeOne(matrix);
        int n = matrix.get(0).size();
        for (int i = col-1 ; i >=0; i--) {
            for (int j = i-1; j >=0; j--) {

                Pair<Integer, Integer> factor = new Pair<>((matrix.get(j).get(i).getKey()*matrix.get(i).get(i).getValue()),matrix.get(i).get(i).getKey()*matrix.get(j).get(i).getValue());
                for (int k = n-1; k>=i ; k--) {

                    int numerator = matrix.get(j).get(k).getKey() * factor.getValue() * matrix.get(i).get(k).getValue() - (matrix.get(j).get(k).getValue() * factor.getKey() * matrix.get(i).get(k).getKey());
                    int denominator = matrix.get(j).get(k).getValue()*factor.getValue()*matrix.get(i).get(k).getValue();
                    Pair<Integer, Integer> newValue = new Pair<>(numerator, denominator);

                    matrix.get(j).set(k, newValue);

                }}}
       simplifyMatrix(matrix);

        return matrix;
    }
}
