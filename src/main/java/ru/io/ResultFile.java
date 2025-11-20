package ru.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int size = 10;

        int[][] multiplicationTable = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multiplicationTable[i][j] = (i + 1) * (j + 1);
            }
        }

        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    String line = String.format("%4d", multiplicationTable[i][j]);
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