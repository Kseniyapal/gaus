package ru.ac.uniyar.palochkina;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> baseVariables = new ArrayList<>();
        ArrayList<ArrayList<Pair<Integer, Integer>>> matrix = MatrixReader.readMatrix();
        Gauss gaus = new Gauss();
        ArrayList<ArrayList<Pair<Integer, Integer>>> result;
        ArrayList<Integer> listVariables = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size() - 1; i++) {
            listVariables.add(i);
        }
        if (matrix.size() + 1 == matrix.get(0).size()) {
            for (int i = 0; i < matrix.size(); i++)
                baseVariables.add(i);

        } else {
            System.out.println("Enter the base variable numbers");





            Scanner scanner = new Scanner(System.in);
            int j = 0;
            for (int i = 0; i < matrix.size(); i++) {
                System.out.print("Base variable " + (i + 1) + ": ");
                int baseVar = scanner.nextInt();
                baseVariables.add(baseVar);

            }
            for (int i = 0; i < matrix.get(0).size() - 1; i++) {
                if (!baseVariables.contains(i)) {
                    swapColumns(matrix, j, i);
                    Collections.swap(listVariables, j, i);
                    j++;
                }
            }
        }
        matrix = gaus.straightMove(matrix, matrix.size());
        result = gaus.backMove(matrix, matrix.size());
        int check = hasSolution(result);
        if (check == 0) {
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

    public static int hasSolution(ArrayList<ArrayList<Pair<Integer, Integer>>> matrix) {
        int nullRow;
        int nullResponse;

        for (int i = 0; i < matrix.size(); i++) {
            nullRow = 0;
            nullResponse = 0;
            for (int j = 0; j < matrix.get(0).size() - 1; j++) {
                if (matrix.get(i).get(j).getValue() == 0) {
                    System.out.println("There is no solution");
                    return 1;
                }
                if (matrix.get(i).get(j).getKey() == 0) {
                    nullRow++;
                }
            }
            if (matrix.get(matrix.size() - 1).get(matrix.get(0).size() - 1).getValue() == 0) {
                System.out.println("There is no solution");
                return 1;
            }
            if (matrix.get(matrix.size() - 1).get(matrix.get(0).size() - 1).getKey() == 0) {
                nullResponse = 1;
            }
            if (nullResponse == 1 && nullRow == matrix.get(0).size()) {
                System.out.println("There are infinitely many solutions");
            }
            if (nullResponse == 0 && nullRow == matrix.get(0).size()) {
                System.out.println("There is no solution");
                return 1;
            }
        }
        return 0;
    }
}