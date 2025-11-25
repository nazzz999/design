package ru.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        String text = readFile("data/even.txt");
        processNumbers(text);
    }

    private static String readFile(String path) {
        StringBuilder text = new StringBuilder();

        try (FileInputStream input = new FileInputStream(path)) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    private static void processNumbers(String text) {
        String[] numbers = text.split("\\s+");
        for (String numberStr : numbers) {
            try {
                int number = Integer.parseInt(numberStr.trim());
                printEvenStatus(number);
            } catch (NumberFormatException e) {
                System.out.println(numberStr + " is not a valid number.");
            }
        }
    }

    private static void printEvenStatus(int number) {
        if (number % 2 == 0) {
            System.out.println(number + " — четное число.");
        } else {
            System.out.println(number + " — нечетное число.");
        }
    }
}