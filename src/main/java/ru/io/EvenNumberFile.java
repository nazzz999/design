package ru.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split("\\s+");

            for (String numberStr : numbers) {
                try {
                    int number = Integer.parseInt(numberStr.trim());
                    if (number % 2 == 0) {
                        System.out.println(number + " четное число.");
                    }
                    else {
                        System.out.println(number + " нечетное число.");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println(numberStr + " is not a valid number.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}