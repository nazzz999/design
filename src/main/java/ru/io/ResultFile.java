package ru.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        final int SIZE = 10;

        int[][] table = generateMultiplicationTable(SIZE);
        writeToFile(table, "data/dataresult.txt");
    }

    private static int[][] generateMultiplicationTable(int size) {
        int [][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

    private static void writeToFile(int[][] table, String filePath) {
        try (FileOutputStream output = new FileOutputStream(filePath)) {
            for (int[] row : table) {
                for (int value : row) {
                    String line = String.format("%4d", value);
                    output.write(line.getBytes());
                }
                output.write(System.lineSeparator().getBytes());
            }
            System.out.println("Таблица умножения успешно записана в файл.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}