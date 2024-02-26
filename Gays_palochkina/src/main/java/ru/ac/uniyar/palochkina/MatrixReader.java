package ru.ac.uniyar.palochkina;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixReader {
    public static ArrayList<ArrayList<Pair<Integer, Integer>>> readMatrix() {
        ArrayList<ArrayList<Pair<Integer, Integer>>> matrix = new ArrayList<>();
        try {
            File file = new File("matrix.txt");
            Scanner scanner = new Scanner(file);
            int cols = scanner.nextInt();
            int rows = scanner.nextInt();
            for (int i = 0; i < rows; i++) {
                ArrayList<Pair<Integer, Integer>> row = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    int first = 0;
                    int second = 1;
                    String[] tokens = scanner.next().split("/");
                    first = Integer.parseInt(tokens[0]);
                    if (tokens.length > 1) {
                        second = Integer.parseInt(tokens[1]);
                    }
                    row.add(new Pair<>(first, second));
                }
                matrix.add(row);
            }
            System.out.println("Matrix:");
            for (ArrayList<Pair<Integer, Integer>> row : matrix) {
                for (Pair<Integer, Integer> pair : row) {
                    System.out.print("(" + pair.getKey() + "/" + pair.getValue() + ") ");
                }
                System.out.println();
            }
            scanner.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return matrix;
    }
}