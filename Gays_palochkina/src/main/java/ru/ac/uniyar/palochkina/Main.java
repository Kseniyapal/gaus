package ru.ac.uniyar.palochkina;

import javafx.util.Pair;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> baseVariables = new ArrayList<>();
        ArrayList<ArrayList<Pair<Integer, Integer>>> matrix = MatrixReader.readMatrix();
        Gauss gaus = new Gauss();
        ArrayList<ArrayList<Pair<Integer, Integer>>> result;

        if (matrix.size() + 1 == matrix.get(0).size()) {
            for(int i=0;i<matrix.size();i++)
                baseVariables.add(i);


            matrix = gaus.straightMove(matrix, matrix.size());
            result = gaus.backMove(matrix, matrix.size());
            System.out.println("Matrix result:");
            for (ArrayList<Pair<Integer, Integer>> row : result) {
                for (Pair<Integer, Integer> pair : row) {
                    System.out.print("(" + pair.getKey() + "/" + pair.getValue() + ") ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Enter the base variable numbers");

            ArrayList<Integer> listVariables = new ArrayList<>();

            for (int i = 0; i < matrix.get(0).size() -1; i++) {
                listVariables.add(i);
            }

            Scanner scanner = new Scanner(System.in);
            int j=0;
            for (int i = 0; i < matrix.size(); i++) {
                System.out.print("Base variable " + (i + 1) + ": ");
                int baseVar = scanner.nextInt();
                swapColumns(matrix, j, baseVar);
                Collections.swap(listVariables, j, baseVar );
                j++;
            }



            gaus.straightMove(matrix,j);
            result = gaus.backMove(matrix, j);
            System.out.println("Matrix result:");
            for (int i = 0; i < matrix.get(0).size()-1; i++) {
                System.out.print("x"+listVariables.get(i) + " ");
            }
            System.out.println();
            for (ArrayList<Pair<Integer, Integer>> row : result) {
                for (Pair<Integer, Integer> pair : row) {
                    System.out.print("(" + pair.getKey() + "/" + pair.getValue() + ") ");
                }
                System.out.println();
            }
        }
    }
    public static void swapColumns(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix, int oldCol, int newCol) {
        for (ArrayList<Pair<Integer, Integer>> pairs : matrix) {
            Pair<Integer, Integer> temp = pairs.get(oldCol);
            pairs.set(oldCol, pairs.get(newCol));
            pairs.set(newCol, temp);
        }
    }
}

